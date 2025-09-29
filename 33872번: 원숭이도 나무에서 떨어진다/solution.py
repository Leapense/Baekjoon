import sys

sys.setrecursionlimit(10 ** 6)

input = sys.stdin.readline

def solve():
    N, H = map(int, input().split())
    S, E = map(int, input().split())
    
    bananas = [0] + list(map(int, input().split()))
    hawks = [0] + list(map(int, input().split()))
    
    adj = [[] for _ in range(N + 1)]
    for _ in range(N - 1):
        u, v = map(int, input().split())
        adj[u].append(v)
        adj[v].append(u)
        
    max_collected_bananas = -1
    visit_count = [0] * (N + 1)
    
    def dfs(current_node, health, collected_bananas):
        nonlocal max_collected_bananas
        if health == 0:
            if current_node == E:
                max_collected_bananas = max(max_collected_bananas, collected_bananas)
            return
        for neighbor in adj[current_node]:
            if hawks[neighbor] == 1:
                continue
            
            if visit_count[neighbor] >= 2:
                continue
            
            visit_count[neighbor] += 1
            new_bananas = collected_bananas
            if visit_count[neighbor] == 1:
                new_bananas += bananas[neighbor]
                
            dfs(neighbor, health - 1, new_bananas)
            visit_count[neighbor] -= 1
            
    visit_count[S] = 1
    initial_bananas = bananas[S]
    dfs(S, H, initial_bananas)
    
    print(max_collected_bananas)
        
solve()