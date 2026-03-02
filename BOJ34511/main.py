import sys

def main():
    input = sys.stdin.readline
    N, M = map(int, input().split())
    grid = [input().strip() for _ in range(N)]

    ans = 0
    for i in range(N):
        for j in range(M):
            c = grid[i][j]

            if j + 1 < M:
                d = grid[i][j + 1]
                if (c == 'X' and d == 'Y') or (c == 'Y' and d == 'X'):
                    ans += 1

            if i + 1 < N:
                d = grid[i + 1][j]
                if (c == 'X' and d == 'Y') or (c == 'Y' and d == 'X'):
                    ans += 1

    print(ans)

if __name__ == "__main__":
    main()