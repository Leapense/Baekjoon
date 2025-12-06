import sys

sys.setrecursionlimit(10**6)

def solve():
    input = sys.stdin.readline
    
    try:
        line = input().strip()
        if not line:
            return
        T = int(line)
    except ValueError:
        return
    
    for _ in range(T):
        try:
            y, x, n, h = map(int, input().split())
        except ValueError:
            break
        
        grid = []
        for _ in range(y):
            row_str = input().strip()
            grid.append([int(c) for c in row_str])
            
        topple_stack = []
        
        for _ in range(n):
            r, c = map(int, input().split())
            r -= 1
            c -= 1
            
            grid[r][c] += 1
            if grid[r][c] > h:
                topple_stack.append((r, c))
                
        dr = [-1, 1, 0, 0]
        dc = [0, 0, -1, 1]
        
        while topple_stack:
            curr_r, curr_c = topple_stack.pop()
            if grid[curr_r][curr_c] <= h:
                continue
            
            grid[curr_r][curr_c] -= 4
            for i in range(4):
                nr, nc = curr_r + dr[i], curr_c + dc[i]
                if 0 <= nr < y and 0 <= nc < x:
                    grid[nr][nc] += 1
                    if grid[nr][nc] > h:
                        topple_stack.append((nr, nc))
        for row in grid:
            print("".join(map(str, row)))
            
if __name__ == "__main__":
    solve()