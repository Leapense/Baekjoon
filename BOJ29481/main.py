import sys

def solve() -> None:
    input = sys.stdin.readline
    n, m = map(int, input().split())
    A = [list(map(int, input().split())) for _ in range(n)]
    c11, c12, c21, c22 = map(int, input().split())
    B = [[0] * m for _ in range(n)]
    
    for i in range(n):
        for j in range(m):
            value = A[i][j]
            if i > 0 and j > 0:
                value -= B[i - 1][j - 1] * c11
            if i > 0:
                value -= B[i - 1][j] * c12
            if j > 0:
                value -= B[i][j - 1] * c21
                
            B[i][j] = value // c22
            
    for row in B:
        print(*row)
        
if __name__ == "__main__":
    solve()