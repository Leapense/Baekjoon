import sys

def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    jobs = []
    for _ in range(N):
        T, S = map(int, input().split())
        jobs.append((T, S))
    
    jobs.sort(key=lambda x: x[1], reverse=True)
    
    INF = 10 ** 18
    cur = INF
    for T, S in jobs:
        cur = min(cur, S) - T
        
    if cur < 0:
        print(-1)
    else:
        print(cur)
        
if __name__ == "__main__":
    solve()