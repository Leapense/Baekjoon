import sys
from collections import deque

def bfs(a, b, graph, n):
    if a == b:
        return 0
    visited = [False] * (n + 1)
    q = deque([(a, 0)])
    visited[a] = True
    while q:
        x, dist = q.popleft()
        if x == b:
            return dist
        for nx, w in graph[x]:
            if not visited[nx]:
                visited[nx] = True
                q.append((nx, dist + w))
    return -1

def solve():
    input = sys.stdin.readline
    N, M = map(int, input().split())
    graph = [[] for _ in range(N + 1)]
    
    for _ in range(N - 1):
        u, v, w = map(int, input().split())
        graph[u].append((v, w))
        graph[v].append((u, w))
        
    out = []
    for _ in range(M):
        a, b = map(int, input().split())
        out.append(str(bfs(a, b, graph, N)))
    print("\n".join(out))
    
if __name__ == "__main__":
    solve()