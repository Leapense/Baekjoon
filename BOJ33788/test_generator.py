#!/usr/bin/env python3
import random
import sys

MAX_SUM_N = 200_000

def answer_of(s: str) -> str:
    return "YES" if len(s) == 1 or not (s[0] == 'L' and s[-1] == 'R') else "NO"

def make_case(n: int, kind: str, rng: random.Random) -> str:
    assert n >= 1
    if kind == "allL":
        return "L" * n
    if kind == "allR":
        return "R" * n
    if kind == "altLR":
        return "".join("L" if i % 2 == 0 else "R" for i in range(n))
    if kind == "altRL":
        return "".join("R" if i % 2 == 0 else "L" for i in range(n))
    if kind == "bad":
        if n == 1:
            return "L"
        mid = "".join(rng.choice("LR") for _ in range(n - 2))
        return "L" + mid + "R"
    if kind == "good":
        if n == 1:
            return rng.choice("LR")
        left, right = rng.choice([("L", "L"), ("R", "L"), ("R", "R")])
        mid = "".join(rng.choice("LR") for _ in range(n - 2))
        return left + mid + right
    if kind == "random":
        return "".join(rng.choice("LR") for _ in range(n))
    
    raise ValueError(f"unknown kind: {kind}")

def build_cases(seed: int = 1, max_sum_n: int = MAX_SUM_N):
    rng = random.Random(seed)
    cases = []
    sum_n = 0
    
    def add(n: int, kind: str):
        nonlocal sum_n
        if n < 1 or sum_n + n > max_sum_n:
            return False
        cases.append(make_case(n, kind, rng))
        sum_n += n
        return True
    
    fixed = [
        "L", "R",
        "LL", "LR", "RL", "RR",
        "LLR", "LRL", "LRR", "RLL", "RLR", "RRL",
        "LRLR", "RLRL", "LLLL", "RRRR",
        "LRRLRL",
        "LR",
        "RL",
    ]
    for s in fixed:
        if sum_n + len(s) <= max_sum_n:
            cases.append(s)
            sum_n += len(s)
            
    small_kinds = ["good", "bad", "allL", "allR", "altLR", "altRL", "random"]
    for _ in range(120):
        n = rng.randint(1, 25)
        kind = rng.choice(small_kinds)
        if not add(n, kind):
            break
        
    medium_kinds = ["good", "bad", "altLR", "altRL", "random"]
    for _ in range(80):
        n = rng.randint(26, 500)
        kind = rng.choice(medium_kinds)
        if not add(n, kind):
            break
        
    large_plan = [
        ("bad", 40000),
        ("good", 40000),
        ("altLR", 35000),
        ("altRL", 35000),
        ("random", 30000),
        ("allL", 10000),
        ("allR", 10000),
    ]
    for kind, want in large_plan:
        n = min(want, max_sum_n - sum_n)
        if n > 0:
            add(n, kind)
            
    while sum_n < max_sum_n:
        remain = max_sum_n - sum_n
        n = min(remain, rng.randint(1, min(1000, remain)))
        kind = rng.choice(["good", "bad", "random"])
        add(n, kind)
        
    return cases

def main():
    seed = int(sys.argv[1]) if len(sys.argv) >= 2 else 1
    max_sum_n = int(sys.argv[2]) if len(sys.argv) >= 3 else MAX_SUM_N
    cases = build_cases(seed=seed, max_sum_n=max_sum_n)
    print(len(cases))
    for s in cases:
        print(len(s))
        print(s)
        
    #for s in cases:
    #    print(answer_of(s))
        
if __name__ == "__main__":
    main()