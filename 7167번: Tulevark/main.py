import sys
from collections import deque

def solve():
    data = list(map(int, sys.stdin.read().split()))
    N = data[0]
    A = data[1:1+N]
    
    dirs = [
        (0, 1), (1, 1), (1, 0), (1, -1),
        (0, -1), (-1, -1), (-1, 0), (-1, 1)
    ]
    
    colored = set()
    seen = set()
    
    q = deque()
    q.append((0, 0, 0, 0))
    
    while q:
        x, y, d, s = q.popleft()
        state = (x, y, d, s)
        if state in seen:
            continue
        seen.add(state)
        
        L = A[s]
        dx, dy = dirs[d]
        
        cx, cy = x, y
        for _ in range(L):
            cx += dx
            cy += dy
            colored.add((cx, cy))
            
        if s + 1 < N:
            q.append((cx, cy, (d - 1) % 8, s + 1))
            q.append((cx, cy, (d + 1) % 8, s + 1));
            
    print(len(colored))
    
if __name__ == "__main__":
    solve()
    