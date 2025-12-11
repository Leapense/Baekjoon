import sys

def main():
    input = sys.stdin.readline
    H, N = map(int, input().split())
    weights = [int(input()) for _ in range(N)]
    dp = [False] * (H + 1)
    dp[0] = True
    
    dp = 1
    mask = (1 << (H + 1)) - 1
    for w in weights:
        dp |= (dp << w)
        dp &= mask
        
    for s in range(H, -1, -1):
        if (dp >> s) & 1:
            print(s)
            return
        
if __name__ == "__main__":
    main()