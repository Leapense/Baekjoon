import sys
import heapq

INF = 10 ** 30
def dijkstra_min_cost(start_digit, nodes, weight, M):
    idx = {d : i for i, d in enumerate(nodes)}
    n = len(nodes)
    
    dist = [INF] * n
    s = idx[start_digit]
    dist[s] = 0
    pq = [(0, s)]
    while pq:
        curd, u = heapq.heappop(pq)
        if curd != dist[u]:
            continue
        a = nodes[u]
        for b in nodes:
            if b == a:
                continue
            t = ((b - a) * weight) % M
            if t == 0:
                continue
            v = idx[b]
            nd = curd + t
            if nd < dist[v]:
                dist[v] = nd
                heapq.heappush(pq, (nd, v))
    return {nodes[i]: dist[i] for i in range(n)}

def main():
    data = sys.stdin.readline().strip().split()
    N = int(data[0])
    M = int(data[1])
    
    s = str(N)
    L = len(s)
    digits = list(map(int, s))
    
    weights = [0] * L
    cur = 1 % M
    for i in range(L - 1, -1, -1):
        weights[i] = cur
        cur = (cur * 10) % M
        
    r0 = N % M
    budget = (M - 1) - r0
    if budget <= 0:
        print(s)
        return
    
    min_costs = []
    for i in range(L):
        if i == 0:
            nodes = list(range(1, 10))
        else:
            nodes = list(range(10))
        mc = dijkstra_min_cost(digits[i], nodes, weights[i], M)
        min_costs.append(mc)
    rem = budget
    out = []
    for i in range(L):
        if i == 0:
            candidates = range(9, 0, -1)
        else:
            candidates = range(9, -1, -1)
            
        chosen = digits[i]
        chosen_cost = 0
        for d in candidates:
            c = min_costs[i].get(d, INF)
            if c <= rem:
                chosen = d
                chosen_cost = c
                break
        out.append(str(chosen))
        rem -= chosen_cost
    print("".join(out))

if __name__ == "__main__":
    main()
    