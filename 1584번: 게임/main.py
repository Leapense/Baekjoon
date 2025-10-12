import sys
from collections import deque

INF = 10**9
MAXC = 500

def read_ints():
    return list(map(int, sys.stdin.buffer.read().split()))

def clamp(a, lo=0, hi=MAXC):
    return max(lo, min(hi, a))

def main():
    data = read_ints()
    it = iter(data)
    
    try:
        N = next(it)
    except StopIteration:
        return
    
    grid = [[0] * (MAXC + 1) for _ in range(MAXC + 1)]
    for _ in range(N):
        x1, y1, x2, y2 = next(it), next(it), next(it), next(it)
        lx, rx = sorted((clamp(x1), clamp(x2)))
        ly, ry = sorted((clamp(y1), clamp(y2)))
        for y in range(ly, ry + 1):
            row = grid[y]
            for x in range(lx, rx + 1):
                if row[x] != -1:
                    row[x] = 1
                    
    M = next(it)
    for _ in range(M):
        x1, y1, x2, y2 = next(it), next(it), next(it), next(it)
        lx, rx = sorted((clamp(x1), clamp(x2)))
        ly, ry = sorted((clamp(y1), clamp(y2)))
        for y in range(ly, ry + 1):
            row = grid[y]
            for x in range(lx, rx + 1):
                row[x] = -1
                
                
    dist = [[INF] * (MAXC + 1) for _ in range(MAXC + 1)]
    dq = deque()
    dist[0][0] = 0
    dq.append((0, 0))
    
    dirs = ((1,0),(-1,0),(0,1),(0,-1))
    while dq:
        x, y = dq.popleft()
        cur = dist[y][x]
        if (x, y) == (MAXC, MAXC):
            print(cur)
            return
        for dx, dy in dirs:
            nx, ny = x + dx, y + dy
            if 0 <= nx <= MAXC and 0 <= ny <= MAXC:
                cell = grid[ny][nx]
                if cell == -1:
                    continue
                add = 1 if cell == 1 else 0
                nd = cur + add
                if nd < dist[ny][nx]:
                    dist[ny][nx] = nd
                    if add == 0:
                        dq.appendleft((nx, ny))
                    else:
                        dq.append((nx, ny))
                        
    print(-1)
    
if __name__ == "__main__":
    main()