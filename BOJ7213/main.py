import sys
from heapq import heappush, heappop

INF = 10 ** 18
class Edge:
    __slots__ = ("to", "rev", "cap", "cost")
    def __init__(self, to, rev, cap, cost):
        self.to = to
        self.rev = rev
        self.cap = cap
        self.cost = cost
        
def add_edge(g, u, v, cap, cost):
    g[u].append(Edge(v, len(g[v]), cap, cost))
    g[v].append(Edge(u, len(g[u]) - 1, 0, -cost))
    
def min_cost_flow(left, right, cost_matrix):
    n = 8
    s, t = 0, 7
    g = [[] for _ in range(n)]
    
    N = sum(left)
    
    for i, c in enumerate(left):
        add_edge(g, s, 1 + i, c, 0)
    for j, c in enumerate(right):
        add_edge(g, 4 + j, t, c, 0)
        
    for i in range(3):
        for j in range(3):
            add_edge(g, 1 + i, 4 + j, N, cost_matrix[i][j])
            
    dist = [INF] * n
    dist[s] = 0
    for _ in range(n - 1):
        updated = False
        for u in range(n):
            if dist[u] == INF:
                continue
            for e in g[u]:
                if e.cap > 0 and dist[e.to] > dist[u] + e.cost:
                    dist[e.to] = dist[u] + e.cost
                    updated = True
                    
        if not updated:
            break
        
    pot = [0] * n
    for v in range(n):
        if dist[v] < INF:
            pot[v] = dist[v]
            
    flow = 0
    total_cost = 0
    
    while flow < N:
        dist = [INF] * n
        dist[s] = 0
        prev_v = [-1] * n
        prev_e = [-1] * n
        
        pq = [(0, s)]
        while pq:
            d, u = heappop(pq)
            if d != dist[u]:
                continue
            for ei, e in enumerate(g[u]):
                if e.cap <= 0:
                    continue
                nd = d + e.cost + pot[u] - pot[e.to]
                if nd < dist[e.to]:
                    dist[e.to] = nd
                    prev_v[e.to] = u
                    prev_e[e.to] = ei
                    heappush(pq, (nd, e.to))
                    
        if dist[t] == INF:
            break
        
        for v in range(n):
            if dist[v] < INF:
                pot[v] += dist[v]
        add = N - flow
        v = t
        while v != s:
            u = prev_v[v]
            e = g[u][prev_e[v]]
            add = min(add, e.cap)
            v = u
        v = t
        
        while v != s:
            u = prev_v[v]
            e = g[u][prev_e[v]]
            e.cap -= add
            g[v][e.rev].cap += add
            total_cost += add * e.cost
            v = u
            
        flow += add
        
    return total_cost

def main():
    raw_data = sys.stdin.read(4096)
    try:
        data = list(map(int, raw_data.split()))
    except ValueError:
        print("Error: Input must contain valid integers.")
        sys.exit(1)
        
    if len(data) != 6:
        print(f"Error: Expected exactly 6 integers, got {len(data)}.")
        sys.exit(1)
        
    if any(val < 0 for val in data):
        print("Error: All values must be non-negative.")
        sys.exit(1)
        
    if sum(data) >= INF // 10:
        print("Error: Input capacities exceed maximum supported computational bounds.")
        sys.exit(1)
        
    a1, p1, z1, a2, p2, z2 = data
    left = [a1, p1, z1]
    right = [a2, p2, z2]
    
    if sum(left) != sum(right):
        print("Error: Left side sum must equal Right side sum for a valid flow constraint.")
        sys.exit(1)
        
    beats = {0: 2, 1: 0, 2: 1}
    score = [[0] * 3 for _ in range(3)]
    for i in range(3):
        for j in range(3):
            if i == j:
                score[i][j] = 0
            elif j == beats[i]:
                score[i][j] = 1
            else:
                score[i][j] = -1
                
    cost_for_max = [[-score[i][j] for j in range(3)] for i in range(3)]
    min_cost = min_cost_flow(left, right, cost_for_max)
    max_score = -min_cost
    min_score = min_cost_flow(left, right, score)
    
    print(max_score)
    print(min_score, end = '')

if __name__ == "__main__":
    main()