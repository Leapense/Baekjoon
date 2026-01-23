import sys

def rotate_ccw(mat):
    n = len(mat)
    return [[mat[j][n - 1 - i] for j in range(n)] for i in range(n)]

def is_strictly_increasing_rows_cols(mat):
    n = len(mat)
    for i in range(n):
        for j in range(n):
            if j > 0 and not (mat[i][j - 1] < mat[i][j]):
                return False
            if i > 0 and not (mat[i - 1][j] < mat[i][j]):
                return False
    return True

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    a = [list(map(int, input().split())) for _ in range(n)]

    cur = a
    for r in range(4):
        if is_strictly_increasing_rows_cols(cur):
            print(r, end="")
            return
        cur = rotate_ccw(cur)

if __name__ == "__main__":
    main()