import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    it = iter(data)
    n = next(it)
    k = next(it)
    x = next(it)
    y = next(it)
    
    q = next(it)
    queries = [next(it) for _ in range(q)]
    
    m = n // k
    S = m * x + (n - m) * y
    
    def floor_of(a: int) -> int:
        p = (a - 1) % S + 1
        lo, hi = 1, n
        while lo < hi:
            mid = (lo + hi) // 2
            t = mid // k
            pref = t * x + (mid - t) * y
            if pref >= p:
                hi = mid
            else:
                lo = mid + 1
        return lo
    
    out = []
    for a in queries:
        out.append(str(floor_of(a)))
        
    sys.stdout.write("\n".join(out))
    
if __name__ == "__main__":
    main()