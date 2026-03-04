import sys
import itertools

def solve():
    n = int(sys.stdin.readline().strip())
    C = [[0] * 3 for _ in range(3)]
    for p0 in range(3 * n):
        block = p0 // n
        res = p0 % 3
        C[block][res] += 1
        
    perms = list(itertools.permutations([0, 1, 2]))
    matchings = []
    for _ in range(n):
        chosen = None
        for perm in perms:
            if all(C[r][perm[r]] > 0 for r in range(3)):
                chosen = perm
                break
            
        if chosen is None:
            raise RuntimeError("No valid matching found (unexpected).")
        
        for r in range(3):
            C[r][chosen[r]] -= 1
        matchings.append(chosen)
    
    bags = [[[] for _ in range(3)] for _ in range(3)]
    for i, perm in enumerate(matchings, start=1):
        for block in range(3):
            res = perm[block]
            bags[block][res].append(i)
            
    ans = []
    for block in range(3):
        for j in range(1, n + 1):
            p0 = block * n + (j - 1)
            res = p0 % 3
            ans.append(bags[block][res].pop())
            
    print(*ans)
    
if __name__ == "__main__":
    solve()