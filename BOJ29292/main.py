import sys

def solve():
    data = list(map(int, sys.stdin.buffer.read().split()))
    if not data:
        return
    
    it = iter(data)
    n = next(it)
    X = next(it)

    a = [next(it) for _ in range(n)]
    b = [next(it) for _ in range(X)]

    total = sum(a)
    sum_b = sum(b)

    idx = 0
    for k in range(X):
        if idx >= n:
            print(-1)
            return

        need = b[k]
        s = 0
        while idx < n and s < need:
            s += a[idx]
            idx += 1

        if s < need:
            print(-1)
            return

        remaining_elements = n - idx
        remaining_segments = X - (k + 1)
        if remaining_elements < remaining_segments:
            print(-1)
            return

        
    print(total - sum_b)

if __name__ == "__main__":
    solve()