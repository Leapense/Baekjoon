import sys
from collections import deque

def main():
    input = sys.stdin.readline
    N, M, K = map(int, input().split())
    total_nodes = N + 1
    
    graph = [[] for _ in range(total_nodes + 1)]
    for _ in range(M):
        u, v = map(int, input().split())
        graph[u].append(v)
        graph[v].append(u)
        
    locked_list = list(map(int, input().split()))
    locked = [False] * (total_nodes + 1)
    for x in locked_list:
        locked[x] = True
        
    if locked[1]:
        print(0)
        return
    
    visited = [False] * (total_nodes + 1)
    q = deque([1])
    visited[1] = True
    
    while q:
        u = q.popleft()
        for v in graph[u]:
            if not locked[v] and not visited[v]:
                visited[v] = True
                q.append(v)
                
    ans = 0
    for i in range(2, total_nodes + 1):
        if not locked[i] and visited[i]:
            ans += 1
            
    print(ans)
    
if __name__ == "__main__":
    main()