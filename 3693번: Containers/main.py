import sys
import math

def best_layout(n):
    S = (n + 4) // 5
    limit = int(math.isqrt(S)) + 1
    best = None
    
    for i in range(1, limit + 1):
        for r, c in ((i, (S + i - 1) // i), ((S + i - 1) // i, i)):
            Lr = 44 * r + 4
            Lc = 10 * c + 2
            L = max(Lr, Lc)
            W = min(Lr, Lc)
            area = L * W
            diff = L - W
            cand = (area, diff, L, W)
            if best is None or cand < best:
                best = cand
                
    area, diff, L, W = best
    return L, W, area

def main():
    it = iter(sys.stdin.read().strip().split())
    t = int(next(it))
    out_lines = []
    for _ in range(t):
        n = int(next(it))
        L, W, A = best_layout(n)
        out_lines.append(f"{L} X {W} = {A}")
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    main()