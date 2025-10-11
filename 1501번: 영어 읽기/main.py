import sys

def signature(word: str):
    n = len(word)
    if n <= 2:
        mid = ""
    else:
        mid = ''.join(sorted(word[1:-1]))
    return (n, word[0], word[-1], mid)

def main():
    input = sys.stdin.readline
    N_line = input().strip()
    while N_line == '':
        N_line = input().strip()
    N = int(N_line)
    
    sig_count = {}
    for _ in range(N):
        w = input().strip()
        if not w:
            continue
        key = signature(w)
        sig_count[key] = sig_count.get(key, 0) + 1
        
    M_line = input().strip()
    while M_line == '':
        M_line = input().strip()
    M = int(M_line)
    
    word_cache = {}
    out_lines = []
    
    for _ in range(M):
        line = input().rstrip('\n')
        if not line.strip():
            out_lines.append("0")
            continue
        ways = 1
        for w in line.split():
            if w in word_cache:
                cnt = word_cache[w]
            else:
                cnt = sig_count.get(signature(w), 0)
                word_cache[w] = cnt
            if cnt == 0:
                ways = 0
                break
            ways *= cnt
        out_lines.append(str(ways))
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()