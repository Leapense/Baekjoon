import sys

data = sys.stdin.read().split()
index = 0

K = int(data[index])
index += 1

for cas in range(1, K + 1):
    n = int(data[index])
    m = int(data[index + 1])
    B = int(data[index + 2])
    index += 3
    
    a = []
    for i in range(n):
        row = []
        for j in range(m + 1):
            row.append(float(data[index]))
            index += 1
        a.append(row)
        
    dp = [[0.0] * (B + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for b in range(B + 1):
            dp[i][b] = dp[i - 1][b]
            for j in range(1, min(m, b) + 1):
                dp[i][b] = max(dp[i][b], dp[i - 1][b - j] + a[i - 1][j])
                
    print(f"Data Set {cas}:")
    print("{:.2f}".format(dp[n][B]))
    print()