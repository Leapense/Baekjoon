import sys
sys.setrecursionlimit(10**6)

def solve():
    data = sys.stdin.read().strip().split()
    N = int(data[0])
    P = int(data[1])
    Q = int(data[2])
    X = int(data[3])
    Y = int(data[4])

    memo = {}

    def A(i):
        if i <= 0:
            return 1
        if i in memo:
            return memo[i]
        left = A(i // P - X)
        right = A(i // Q - Y)
        memo[i] = left + right
        return memo[i]

    print(A(N))

if __name__ == "__main__":
    solve()
