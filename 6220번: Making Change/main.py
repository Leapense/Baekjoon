import sys

def main():
    data = sys.stdin.buffer.read().split()
    C = int(data[0])
    N = int(data[1])
    coins = list(map(int, data[2:2+N]))

    INF = 10**9
    dp = [INF] * (C + 1)
    dp[0] = 0

    for amount in range(1, C + 1):
        best = INF
        for coin in coins:
            if amount >= coin:
                v = dp[amount - coin] + 1
                if v < best:
                    best = v

        dp[amount] = best

    print(dp[C])

if __name__ == "__main__":
    main()