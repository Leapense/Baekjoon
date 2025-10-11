import sys

def cv(c: str) -> int:
    if '0' <= c <= '9':
        return ord(c) - ord('0')
    return -(ord(c) - ord('A') + 1)

def count_cycles(perm):
    n = len(perm)
    vis = [False] * n
    cycles = 0
    for i in range(n):
        if not vis[i]:
            cycles += 1
            j = i
            while not vis[j]:
                vis[j] = True
                j = perm[j]
    return cycles

def solve():
    data = sys.stdin.read().strip().splitlines()
    N = int(data[0].strip())
    grid = [list(map(cv, line.strip())) for line in data[1:1+N]]
    
    used_col = [False] * N
    perm = [-1] * N
    min_score = None
    max_score = None
    
    def dfs(r, prod):
        nonlocal min_score, max_score
        if r == N:
            K = count_cycles(perm)
            score = -prod if (K % 2 == 0) else prod
            if min_score is None or score < min_score:
                min_score = score
            if max_score is None or score > max_score:
                max_score = score
            return
        for c in range(N):
            if not used_col[c]:
                used_col[c] = True
                perm[r] = c
                dfs(r + 1, prod * grid[r][c])
                used_col[c] = False
                
    dfs(0, 1)
    print(min_score)
    print(max_score)
    
if __name__ == "__main__":
    solve()