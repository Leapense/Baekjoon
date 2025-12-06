import sys
from collections import deque

sys.setrecursionlimit(10 ** 6)
def solve():
    try:
        line1 = sys.stdin.readline().split()
        if not line1:
            return
        H, W, N = map(int, line1)
    except ValueError:
        return
    
    grid = []
    points = [None] * (N + 1)
    for r in range(H):
        row_str = sys.stdin.readline().strip()
        grid.append(row_str)
        for c in range(W):
            char = row_str[c]
            if char == 'S':
                points[0] = (r, c)
            elif '1' <= char <= '9':
                idx = int(char)
                points[idx] = (r, c)
                
    def get_shortest_dist(start_pos, end_pos):
        sr, sc = start_pos
        er, ec = end_pos
        
        queue = deque([(sr, sc, 0)])
        visited = [[False] * W for _ in range(H)]
        visited[sr][sc] = True
        
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        while queue:
            curr_r, curr_c, dist = queue.popleft()
            if curr_r == er and curr_c == ec:
                return dist
            for dr, dc in directions:
                nr, nc = curr_r + dr, curr_c + dc
                if 0 <= nr < H and 0 <= nc < W:
                    if not visited[nr][nc] and grid[nr][nc] != 'X':
                        visited[nr][nc] = True
                        queue.append((nr, nc, dist + 1))
        return 0
    
    total_time = 0
    for i in range(N):
        start_node = points[i]
        target_node = points[i + 1]
        dist = get_shortest_dist(start_node, target_node)
        total_time += dist
    print(total_time)
    
if __name__ == '__main__':
    solve()