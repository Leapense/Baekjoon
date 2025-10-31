import sys
import math

def solve():
    data = list(map(int, sys.stdin.read().split()))
    it = iter(data)
    d = next(it)
    out_lines = []
    for _ in range(d):
        n = next(it)
        xs = [next(it) for _ in range(n)]
        if n == 2:
            out_lines.append("YES" if xs[0] % xs[1] == 0 else "NO")
            continue
        r = xs[1]
        for y in [xs[0]] + xs[2:]:
            g = math.gcd(r, y)
            if g > 1:
                r //= g
                if r == 1:
                    break
        out_lines.append("YES" if r == 1 else "NO")
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    solve()