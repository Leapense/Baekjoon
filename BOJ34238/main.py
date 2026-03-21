import sys

input = sys.stdin.readline

N, M = map(int, input().split())
board = [input().strip() for _ in range(N)]

directions = [
    (-1, 0), (1, 0), (0, -1), (0, 1),
    (-1, -1), (-1, 1), (1, -1), (1, 1)
]

answer = 0
for r in range(N):
    for c in range(M):
        if board[r][c] != 'F':
            continue
        for dr, dc in directions:
            r1, c1 = r + dr, c + dc
            r2, c2 = r + 2 * dr, c + 2 * dc
            if 0 <= r2 < N and 0 <= c2 < M:
                if board[r1][c1] == 'O' and board[r2][c2] == 'X':
                    answer += 1
                    
print(answer, end='')