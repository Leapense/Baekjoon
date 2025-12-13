import sys

def main():
    input = sys.stdin.readline
    N, C = map(int, input().split())
    coins = [int(input().strip()) for _ in range(C)]
    dp = [0] * (N + 1)
    dp[0] = 1
    
    for coin in coins:
        for x in range(coin, N + 1):
            dp[x] += dp[x - coin]
            
    print(dp[N])
    
if __name__ == "__main__":
    main()