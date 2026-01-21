import sys

def main():
    input = sys.stdin.readline
    n, m, a, b = map(int, input().split())
    
    INF = 10**18
    dp = [INF] * m
    dp[0] = 0
    for _ in range(n):
        ndp = [INF] * m
        min_pref = dp[0]
        min_all = dp[0]
        
        for t in range(1, m + 1):
            if t < m:
                dpt = dp[t]
                if dpt < min_all:
                    min_all = dpt
                val = dpt - t * b
                if val < min_pref:
                    min_pref = val
                    
            ndp[t - 1] = t * b + min_pref
        reload_cost = min_all + a
        if reload_cost < ndp[m - 1]:
            ndp[m - 1] = reload_cost
        dp = ndp
    ans = n + min(dp)
    print(ans)
    
if __name__ == "__main__":
    main()