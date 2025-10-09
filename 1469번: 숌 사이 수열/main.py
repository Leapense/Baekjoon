import sys

def solve():
    data = sys.stdin.read().strip().split()
    if not data:
        return
    it = iter(data)
    N = int(next(it))
    X = [int(next(it)) for _ in range(N)]
    X.sort()
    
    L = 2 * N
    S = [-1] * L
    
    for v in X:
        if v > L - 2:
            print(-1)
            return
        
    used = {v : False for v in X}
    def next_empty(start):
        i = start
        while i < L and S[i] != -1:
            i += 1
        return i
    
    def dfs(start_idx):
        p = next_empty(start_idx)
        if p == L:
            return True
        
        for v in X:
            if not used[v]:
                q = p + v + 1
                if q < L and S[p] == -1 and S[q] == -1:
                    S[p] = S[q] = v
                    used[v] = True
                    if dfs(p + 1):
                        return True
                    S[p] = S[q] = -1
                    used[v] = False
        return False
    
    if dfs(0):
        print(" ".join(map(str, S)))
    else:
        print(-1)
        
if __name__ == "__main__":
    solve()