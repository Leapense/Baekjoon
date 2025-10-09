import sys

MOD = 900528

def main():
    A = sys.stdin.readline().rstrip('\n')
    T = sys.stdin.readline().rstrip('\n')
    K = len(A)
    n = len(T)
    
    pos = {ch: i for i, ch in enumerate(A)}
    
    base = 0
    for ch in T:
        base = (base * K + pos[ch]) % MOD
    rank_mod = (base + 1) % MOD
    
    sum_len = 0
    powk = 1
    for _ in range(n - 1):
        powk = (powk * K) % MOD
        sum_len = (sum_len + powk) % MOD
        
    ans = (sum_len + rank_mod) % MOD
    print(ans)
    
if __name__ == "__main__":
    main()