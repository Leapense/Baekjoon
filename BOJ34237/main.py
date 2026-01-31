import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    it = iter(data)
    N = next(it)
    M = next(it)
    
    bets = [(next(it), next(it)) for _ in range(N)]
    out_lines = []
    for _ in range(M):
        g = next(it)
        x = next(it)
        y = next(it)
        cnt = 0
        for a, b in bets:
            if a >= x and b >= y and a + b <= g:
                cnt += 1
        out_lines.append(str(cnt))
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()