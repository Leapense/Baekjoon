import sys

AORD = ord('A')

def word_profile(w: str):
    need = [0] * 26
    for ch in w:
        need[ord(ch) - AORD] += 1
    letters = [i for i in range(26) if need[i] > 0]
    req = [need[i] for i in letters]
    return letters, req

def main():
    data = sys.stdin.read().strip().split()
    it = iter(data)
    dict_profiles = []
    for token in it:
        if token == '-':
            break
        letters, req = word_profile(token)
        dict_profiles.append((letters, req))
    
    out_lines = []
    for token in it:
        if token == '#':
            break
        board_str = token
        board = [0] * 26
        for ch in board_str:
            board[ord(ch) - AORD] += 1
            
        center_cnt = [0] * 26
        
        for letters, req in dict_profiles:
            ok = True
            for li, rq in zip(letters, req):
                if board[li] < rq:
                    ok = False
                    break
            if not ok:
                continue
            for li in letters:
                center_cnt[li] += 1
                
        candidates = [i for i in range(26) if board[i] > 0]
        vals = [center_cnt[i] for i in candidates]
        minv = min(vals) if vals else 0
        maxv = max(vals) if vals else 0
        
        mins = ''.join(chr(i + AORD) for i in candidates if center_cnt[i] == minv)
        maxs = ''.join(chr(i + AORD) for i in candidates if center_cnt[i] == maxv)
        
        out_lines.append(f"{mins} {minv} {maxs} {maxv}")
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()