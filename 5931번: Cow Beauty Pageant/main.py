import sys
from collections import deque

input = sys.stdin.readline

def main():
    N, M = map(int, input().split())
    grid = [list(input().strip()) for _ in range(N)]
    visited = [[False] * M for _ in range(N)]
    spots = []
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    for i in range(N):
        for j in range(M):
            if grid[i][j] == 'X' and not visited[i][j]:
                comp = []
                q = deque()
                q.append((i, j))
                visited[i][j] = True
                
                while q:
                    r, c = q.popleft()
                    comp.append((r, c))
                    for dr, dc in directions:
                        nr, nc = r + dr, c + dc
                        if 0 <= nr < N and 0 <= nc < M:
                            if not visited[nr][nc] and grid[nr][nc] == 'X':
                                visited[nr][nc] = True
                                q.append((nr, nc))
                                
                spots.append(comp)
    spot1, spot2 = spots[0], spots[1]
    INF = 10 ** 9
    ans = INF
    
    for r1, c1 in spot1:
        for r2, c2 in spot2:
            dist = abs(r1 - r2) + abs(c1 - c2) - 1
            if dist < ans:
                ans = dist
    print(ans)
    
if __name__ == '__main__':
    main()