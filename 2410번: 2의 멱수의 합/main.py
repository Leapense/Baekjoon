import sys
from array import array

MOD = 1_000_000_000

def main():
    data = sys.stdin.buffer.readline().strip()
    N = int(data)
    
    dp = array('I', [0]) * (N + 1)
    dp[0] = 1
    for i in range(1, N + 1):
        v = dp[i - 1]
        if (i & 1) == 0:
            v += dp[i >> 1]
        dp[i] = v % MOD
        
    print(dp[N])
    
if __name__ == "__main__":
    main()