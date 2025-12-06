import sys
from bisect import bisect_left

input = sys.stdin.readline

def generate_subsets(flows, costs):
    n = len(flows)
    subsets = []
    
    for mask in range(1 << n):
        sum_f = 0
        sum_c = 0
        for i in range(n):
            if mask & (1 << i):
                sum_f += flows[i]
                sum_c += costs[i]
        subsets.append((sum_f, sum_c))
    return subsets

def main():
    n = int(input())
    F = []
    C = []
    for _ in range(n):
        f, c = map(int, input().split())
        F.append(f)
        C.append(c)
        
    m = int(input())
    queries = []
    for _ in range(m):
        V, T = map(int, input().split())
        queries.append((V, T))
        
    n1 = n // 2
    F1, C1 = F[:n1], C[:n1]
    F2, C2 = F[n1:], C[n1:]
    
    left_subsets = generate_subsets(F1, C1)
    right_subsets = generate_subsets(F2, C2)
    
    right_subsets.sort()
    flowsB = []
    costsB = []
    
    prev_flow = None
    for flow, cost in right_subsets:
        if prev_flow is None or flow != prev_flow:
            flowsB.append(flow)
            costsB.append(cost)
            prev_flow = flow
        else:
            if cost < costsB[-1]:
                costsB[-1] = cost
                
    INF = 10 ** 30
    nB = len(flowsB)
    sufMin = [INF] * nB
    best = INF
    for i in range(nB - 1, -1, -1):
        if costsB[i] < best:
            best = costsB[i]
        sufMin[i] = best
    
    case_no = 1
    for V, T in queries:
        D = (V + T - 1) // T
        ans = INF
        
        for flowA, costA in left_subsets:
            if flowA >= D:
                if costA < ans:
                    ans = costA
            else:
                need = D - flowA
                idx = bisect_left(flowsB, need)
                if idx < nB:
                    total_cost = costA + sufMin[idx]
                    if total_cost < ans:
                        ans = total_cost
                        
        if ans == INF:
            print(f"Case {case_no}: IMPOSSIBLE")
        else:
            print(f"Case {case_no}: {ans}")
            
        case_no += 1
            
if __name__ == "__main__":
    main()