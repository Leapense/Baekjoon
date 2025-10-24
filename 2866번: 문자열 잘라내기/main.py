import sys

def main():
    input = sys.stdin.readline
    R, C = map(int, input().split())
    rows = [input().strip() for _ in range(R)]
    columns = [''.join(col) for col in zip(*rows)]
    
    def is_unique(k: int) -> bool:
        seen = set()
        for col in columns:
            s = col[k:]
            if s in seen:
                return False
            seen.add(s)
        return True
    
    lo, hi = 0, R - 1
    ans = 0
    while lo <= hi:
        mid = (lo + hi) // 2
        if is_unique(mid):
            ans = mid
            lo = mid + 1
        else:
            hi = mid - 1
    print(ans)
    
if __name__ == "__main__":
    main()