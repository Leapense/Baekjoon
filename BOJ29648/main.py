import sys
import math

def solve():
    a, b, S = map(int, sys.stdin.readline().split())
    d = b - a

    r = int(math.isqrt(S))
    for p in range(1, r + 1):
        if S % p != 0:
            continue
        q = S // p

        L = None
        if p - q == d:
            L = p + a
        elif q - p == d:
            L = q + a
        
        if L is None:
            continue

        if L > a and L > b and (L - a) * (L - b) == S:
            print(L)
            return
    print(-1)

if __name__ == "__main__":
    solve()