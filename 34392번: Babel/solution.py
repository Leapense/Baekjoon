import sys
from collections import deque

def main() -> None:
    input = sys.stdin.readline
    
    R, C = map(int, input().split())
    grid = [list(input().strip()) for _ in range(R)]
    Q = int(input())
    comp_id = [[-1] * C for _ in range(R)]
    comp_lang = []
    cur_id = 0
    dy = (-1, 1, 0, 0)
    dx = (0, 0, -1, 1)
    
    for r in range(R):
        for c in range(C):
            if comp_id[r][c] != -1:
                continue
            lang = grid[r][c]
            comp_lang.append(lang)
            comp_id[r][c] = cur_id
            q = deque([(r, c)])
            while q:
                y, x = q.popleft()
                for k in range(4):
                    ny, nx = y + dy[k], x + dx[k]
                    if 0 <= ny < R and 0 <= nx < C and comp_id[ny][nx] == -1 and grid[ny][nx] == lang:
                        comp_id[ny][nx] = cur_id
                        q.append((ny, nx))
            cur_id += 1
            
    out_lines = []
    for _ in range(Q):
        r1, c1, r2, c2 = map(int, input().split())
        id1 = comp_id[r1][c1]
        id2 = comp_id[r2][c2]
        if id1 == id2:
            out_lines.append(comp_lang[id1])
        else:
            out_lines.append('N')
            
    sys.stdout.write('\n'.join(out_lines))
    
if __name__ == "__main__":
    main()