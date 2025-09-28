import sys

def solve():
    input = sys.stdin.readline
    N = int(input().strip())
    if N == 0:
        print("0 0")
        return
    
    visited = [False] * (N + 1)
    visited[0] = True
    
    frontier = {0: 0}
    days = 0
    while True:
        days += 1
        next_cost = {}
        for length, water in frontier.items():
            y = length + 1
            if y <= N and not visited[y]:
                w = water + 1
                if y not in next_cost or w < next_cost[y]:
                    next_cost[y] = w
                    
            y = length * 3
            if y <= N and not visited[y]:
                w = water + 3
                if y not in next_cost or w < next_cost[y]:
                    next_cost[y] = w
                    
            y = length * length
            if y <= N and not visited[y]:
                w = water + 5
                if y not in next_cost or w < next_cost[y]:
                    next_cost[y] = w
                    
        if N in next_cost:
            print(days, next_cost[N])
            return
        
        for k in next_cost.keys():
            visited[k] = True
            
        frontier = next_cost
        
if __name__ == "__main__":
    solve()