import sys

def solve():
    input = sys.stdin.readline
    T = int(input().strip())
    out_lines = []
    for _ in range(T):
        N = int(input().strip())
        coins = list(map(int, input().split()))
        M = int(input().strip())
        
        dp = [0] * (M + 1)
        dp[0] = 1
        
        for coin in coins:
            for x in range(coin, M + 1):
                dp[x] += dp[x - coin]
                
        out_lines.append(str(dp[M]))
    
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    solve()