import argparse
import random
from pathlib import Path


def calc_answer(n, a, b, days):
    """
    Probability that there is at least one day where Audrey is stressed.

    For one day:
        P(relaxed) = overlap_length((a,b), (s,e)) / (b-a)

    Since days are independent:
        P(all relaxed) = product of daily relaxed probabilities
        P(at least one stressed) = 1 - P(all relaxed)
    """
    watch_len = b - a
    p_all_relaxed = 1.0

    for s, e in days:
        overlap = max(0, min(b, e) - max(a, s))
        p_all_relaxed *= overlap / watch_len

    return 1.0 - p_all_relaxed


def save_case(outdir: Path, idx: int, name: str, n: int, a: int, b: int, days):
    in_path = outdir / f"{idx:02d}_{name}.in"
    out_path = outdir / f"{idx:02d}_{name}.out"

    with open(in_path, "w", encoding="utf-8") as f:
        f.write(f"{n} {a} {b}\n")
        for s, e in days:
            f.write(f"{s} {e}\n")

    ans = calc_answer(n, a, b, days)
    with open(out_path, "w", encoding="utf-8") as f:
        f.write(f"{ans:.10f}\n")

    return in_path.name, out_path.name, ans


def rand_ab(rng: random.Random):
    a = rng.randint(1, 49)
    b = rng.randint(a + 1, 50)
    return a, b


def rand_interval(rng: random.Random):
    s = rng.randint(1, 49)
    e = rng.randint(s + 1, 50)
    return s, e


def make_random_general(rng: random.Random):
    n = rng.randint(1, 20)
    a, b = rand_ab(rng)
    days = [rand_interval(rng) for _ in range(n)]
    return n, a, b, days


def make_random_small_watch(rng: random.Random):
    # (b-a)=1 인 매우 좁은 구간
    n = rng.randint(1, 20)
    a = rng.randint(1, 49)
    b = a + 1
    days = [rand_interval(rng) for _ in range(n)]
    return n, a, b, days


def make_random_large_n(rng: random.Random):
    n = 20
    a, b = rand_ab(rng)
    days = [rand_interval(rng) for _ in range(n)]
    return n, a, b, days


def make_random_touching(rng: random.Random):
    # 끝점에 닿기만 하는 케이스를 많이 섞음
    n = rng.randint(8, 20)
    a, b = rand_ab(rng)
    days = []

    patterns = []

    if a > 1:
        patterns.append((1, a))          # 왼쪽 끝점만 접함 -> overlap 0
        patterns.append((a - 1, a))      # 아주 작은 왼쪽 접촉

    if b < 50:
        patterns.append((b, 50))         # 오른쪽 끝점만 접함 -> overlap 0
        if b < 49:
            patterns.append((b, b + 1))  # 아주 작은 오른쪽 접촉

    patterns.append((a, b))                              # 정확히 동일
    patterns.append((max(1, a - 1), min(50, b + 1)))    # 감시 구간 완전 포함
    patterns.append((max(1, a - 3), a + 1))             # 왼쪽 조금 겹침
    patterns.append((b - 1, min(50, b + 3)))            # 오른쪽 조금 겹침

    patterns = [p for p in patterns if 1 <= p[0] < p[1] <= 50]

    for _ in range(n):
        if rng.random() < 0.7:
            days.append(rng.choice(patterns))
        else:
            days.append(rand_interval(rng))

    return n, a, b, days


def make_random_zero_or_full(rng: random.Random):
    # 완전 포함 / 완전 불포함 / 일반 케이스를 섞어서 극단값 자주 생성
    n = rng.randint(1, 20)
    a, b = rand_ab(rng)
    days = []

    for _ in range(n):
        t = rng.random()

        if t < 0.35:
            # full cover
            s = rng.randint(1, a)
            e = rng.randint(b, 50)
            days.append((s, e))

        elif t < 0.70:
            # guaranteed zero overlap
            if a > 1 and (b == 50 or rng.random() < 0.5):
                s = rng.randint(1, a - 1)
                e = rng.randint(s + 1, a)
                days.append((s, e))
            elif b < 50:
                s = rng.randint(b, 49)
                e = rng.randint(s + 1, 50)
                days.append((s, e))
            else:
                # fallback
                days.append((1, a))

        else:
            days.append(rand_interval(rng))

    return n, a, b, days


def build_fixed_cases():
    cases = []

    # 1. 최소 입력, 완전 커버 -> 정답 0
    cases.append(("min_full_cover", 1, 1, 2, [(1, 2)]))

    # 2. 최소 입력, 왼쪽 끝점만 접함 -> 정답 1
    cases.append(("min_touch_left_zero", 1, 10, 11, [(1, 10)]))

    # 3. 최소 입력, 오른쪽 끝점만 접함 -> 정답 1
    cases.append(("min_touch_right_zero", 1, 10, 11, [(11, 50)]))

    # 4. 단일 날짜, 감시 구간과 정확히 동일
    cases.append(("single_exact_same", 1, 19, 39, [(19, 39)]))

    # 5. 단일 날짜, 감시 구간 완전 포함
    cases.append(("single_contains_all", 1, 19, 39, [(1, 50)]))

    # 6. 단일 날짜, 왼쪽 일부만 겹침
    cases.append(("single_partial_left", 1, 10, 20, [(1, 15)]))

    # 7. 단일 날짜, 오른쪽 일부만 겹침
    cases.append(("single_partial_right", 1, 10, 20, [(15, 50)]))

    # 8. 문제 예제
    cases.append(("sample_case", 2, 19, 39, [(18, 31), (28, 50)]))

    # 9. 모든 날 완전 커버 -> 정답 0
    cases.append(("many_all_full", 20, 5, 45, [(1, 50)] * 20))

    # 10. 모든 날 동일한 부분 겹침
    cases.append(("many_same_partial", 20, 10, 30, [(15, 25)] * 20))

    # 11. 하루라도 overlap 0 이면 전체 정답은 1
    days = [(1, 50)] * 19 + [(1, 10)]
    cases.append(("one_zero_among_full", 20, 10, 30, days))

    # 12. 끝점 위주 deterministic 케이스
    cases.append((
        "endpoint_heavy",
        10, 20, 30,
        [
            (1, 20),   # left touch
            (30, 50),  # right touch
            (20, 30),  # exact
            (19, 31),  # contain
            (1, 21),   # tiny left overlap
            (29, 50),  # tiny right overlap
            (10, 25),  # partial left
            (25, 40),  # partial right
            (1, 50),   # full cover
            (21, 29),  # completely inside
        ]
    ))

    # 13. 감시 구간 길이 1
    cases.append((
        "narrow_watch_mixed",
        12, 24, 25,
        [
            (1, 24), (25, 50), (24, 25), (1, 50),
            (23, 25), (24, 26), (10, 20), (20, 24),
            (25, 26), (5, 40), (24, 30), (1, 25)
        ]
    ))

    # 14. 점점 겹침 길이가 커지는 계단형
    staircase_days = [
        (1, 11), (2, 14), (4, 18), (6, 22), (8, 26),
        (10, 30), (12, 34), (14, 38), (16, 42), (18, 46),
        (20, 50), (1, 50)
    ]
    cases.append(("staircase_overlap", 12, 10, 40, staircase_days))

    # 15. 넓은 감시 구간 + 짧은 interval 다수
    cases.append((
        "short_intervals_mix",
        20, 2, 49,
        [
            (1, 2), (49, 50), (2, 3), (48, 49), (10, 11),
            (20, 21), (30, 31), (40, 41), (1, 50), (2, 49),
            (5, 45), (15, 16), (16, 17), (17, 18), (18, 19),
            (19, 20), (25, 26), (26, 27), (27, 28), (28, 29)
        ]
    ))

    return cases


def main():
    parser = argparse.ArgumentParser(
        description="Generate test cases for the Honkai Stress Relief problem."
    )
    parser.add_argument("--seed", type=int, default=20260328, help="random seed")
    parser.add_argument("--outdir", type=str, default="tests", help="output directory")
    parser.add_argument("--random-count", type=int, default=10, help="number of extra random cases")
    args = parser.parse_args()

    rng = random.Random(args.seed)
    outdir = Path(args.outdir)
    outdir.mkdir(parents=True, exist_ok=True)

    manifest = []

    idx = 1
    for name, n, a, b, days in build_fixed_cases():
        manifest.append(save_case(outdir, idx, name, n, a, b, days))
        idx += 1

    random_builders = [
        ("random_general", make_random_general),
        ("random_small_watch", make_random_small_watch),
        ("random_large_n", make_random_large_n),
        ("random_touching", make_random_touching),
        ("random_zero_or_full", make_random_zero_or_full),
    ]

    for _ in range(args.random_count):
        label, builder = rng.choice(random_builders)
        n, a, b, days = builder(rng)
        manifest.append(save_case(outdir, idx, f"{label}_{idx:02d}", n, a, b, days))
        idx += 1

    manifest_path = outdir / "manifest.txt"
    with open(manifest_path, "w", encoding="utf-8") as f:
        f.write(f"seed = {args.seed}\n")
        f.write("generated files:\n")
        for in_file, out_file, ans in manifest:
            f.write(f"{in_file} -> {out_file}, expected = {ans:.10f}\n")

    print(f"Generated {len(manifest)} test cases in: {outdir.resolve()}")
    print(f"Manifest: {manifest_path.resolve()}")


if __name__ == "__main__":
    main()