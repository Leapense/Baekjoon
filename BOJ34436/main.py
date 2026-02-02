import sys

def main():
    input = sys.stdin.buffer.readline
    n_line = input()
    if not n_line:
        return
    n = int(n_line)
    
    out_lines = []
    for _ in range(n):
        line = input()
        if line.endswith(b'\n'):
            line = line[:-1]
            
        counts = [0] * 26
        in_token = False
        got_start = False
        
        for b in line:
            if b == 32:
                in_token = False
                got_start = False
            else:
                if not in_token:
                    in_token = True
                    got_start = False
                if (not got_start) and (97 <= b <= 122):
                    counts[b - 97] += 1
                    got_start = True
                    
        max_cnt = max(counts)
        best_idx = 0
        for i in range(26):
            if counts[i] == max_cnt:
                best_idx = i
                break
        out_lines.append(chr(best_idx + 97))
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()