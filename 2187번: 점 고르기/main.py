import sys

def main():
    data = list(map(int, sys.stdin.read().split()))
    it = iter(data)
    N = next(it); A = next(it); B = next(it)
    points = [(next(it), next(it), next(it)) for _ in range(N)]
    
    dr = A - 1
    dc = B - 1
    
    ans = 0
    for i in range(N):
        r1, c1, s1 = points[i]
        for j in range(i + 1, N):
            r2, c2, s2 = points[j]
            if abs(r1 - r2) <= dr and abs(c1 - c2) <= dc:
                diff = s1 - s2 if s1 >= s2 else s2 - s1
                if diff > ans:
                    ans = diff
                    
    print(ans)
    
if __name__ == "__main__":
    main()