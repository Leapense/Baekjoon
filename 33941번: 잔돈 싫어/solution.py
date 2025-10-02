import sys

def main() -> None:
    it = sys.stdin.buffer.read().split()
    if not it:
        return
    n = int(it[0])
    R = []
    total = 0
    for a in map(int, it[1:]):
        if 500 < a < 20000:
            r = a - 500
            R.append(r)
            total += r
    if total == 0:
        print(0)
        return
    
    INF = 10 ** 18
    dp = [INF] * 500
    dp[0] = 0
    
    for v in R:
        r = v % 500
        cur = dp[:]
        for rem, s in enumerate(cur):
            if s == INF:
                continue
            nr = (rem + r) % 500
            ns = s + v
            if ns < dp[nr]:
                dp[nr] = ns
                
    mod = total % 500
    if mod == 0:
        print(total)
    else:
        delta = dp[mod]
        if delta == INF or total - delta == 0:
            print(0)
        else:
            print(total - delta)
            
if __name__ == "__main__":
    main()