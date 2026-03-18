import sys

input = sys.stdin.readline

w, h = map(int, input().split())
board = [input().strip() for _ in range(h)]

n, m = map(int, input().split())
proc = [input().strip() for _ in range(m)]

pos_cnt = w - n + 1
pos_mask = (1 << pos_cnt) - 1

board_bits = [[0] * 26 for _ in range(h)]
for r in range(h):
    row = board[r]
    bits = [0] * 26
    for col, ch in enumerate(row):
        bits[ord(ch) - 65] |= 1 << col
    board_bits[r] = bits
    
proc_groups = []
for row in proc:
    groups = [[] for _ in range(26)]
    for col, ch in enumerate(row):
        if ch != '*':
            groups[ord(ch) - 65].append(col)
    proc_groups.append(groups)
    
row_match = [[0] * h for _ in range(m)]

for i in range(m):
    groups = proc_groups[i]
    for r in range(h):
        valid = pos_mask
        bits_row = board_bits[r]
        for letter in range(26):
            cols = groups[letter]
            if not cols:
                continue
            
            cur = pos_mask
            letter_bits = bits_row[letter]
            for col in cols:
                cur &= (letter_bits >> col)
                if cur == 0:
                    break
            valid &= cur
            if valid == 0:
                break
            
        row_match[i][r] = valid
        
answer = 0
for top in range(h - m + 1):
    valid = pos_mask
    for i in range(m):
        valid &= row_match[i][top + i]
        if valid == 0:
            break
        
    answer += valid.bit_count()

print(answer, end='')