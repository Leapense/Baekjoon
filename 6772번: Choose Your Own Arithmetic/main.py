import sys

def compute_upper_bound(W: int, digits: list[int]) -> int:
    """
    만들 수 있는 값의 '안전한 상한(bound)'을 계산.
    digits가 모두 0~9의 비음이 아닌 정수이고 연산 +, * 이므로
    현재 값이 클수록 이후에도 최대값이 커진다(단조성).
    따라서 매 단계에서 (x + maxd) vs (x * maxd) 중 큰 값을 고르는
    그리디가 전체 최대값 상한으로 충분하다.
    """
    maxd = max(digits)
    bound = maxd
    for _ in range(W):
        bound = max(bound + maxd, bound * maxd)
    return min(bound, 5_000_000)

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    idx = 0

    W = data[idx]; idx += 1
    D = data[idx]; idx += 1
    digits = data[idx:idx + D]; idx += D
    V = data[idx]; idx += 1
    targets = data[idx:idx + V]

    if W == 0:
        digit_set = set(digits)
        out = ["Y" if t in digit_set else "N" for t in targets]
        sys.stdout.write("\n".join(out))
        return

    bound = compute_upper_bound(W, digits)

    cur_list = list(dict.fromkeys(digits))
    cur_seen = bytearray(bound + 1)
    for d in cur_list:
        if d <= bound:
            cur_seen[d] = 1

    for _ in range(W):
        nxt_seen = bytearray(bound + 1)
        nxt_list = []
        for x in cur_list:
            for d in digits:
                y = x + d
                if y <= bound and not nxt_seen[y]:
                    nxt_seen[y] = 1
                    nxt_list.append(y)

                y = x * d
                if y <= bound and not nxt_seen[y]:
                    nxt_seen[y] = 1
                    nxt_list.append(y)

        cur_list = nxt_list
        cur_seen = nxt_seen

    out = []
    for t in targets:
        out.append("Y" if t <= bound and cur_seen[t] else "N")

    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()