import sys

def solve() -> None:
    input = sys.stdin.readline
    n = int(input().strip())
    wastes = [input().strip() for _ in range(n)]
    
    p, c, v, s, g, f = map(int, input().split())
    o_cost = int(input().strip())
    
    recycle_cost = {
        'P': p,
        'C': c,
        'V': v,
        'S': s,
        'G': g,
        'F': f,
    }
    
    total = 0
    
    for waste in wastes:
        length = len(waste)
        kinds = set(waste)
        if len(kinds) == 1:
            ch = next(iter(kinds))
            if ch in recycle_cost:
                total += length * min(recycle_cost[ch], o_cost)
            else:
                total += length * o_cost
        else:
            total += length * o_cost
            
    print(total, end='')
    
if __name__ == '__main__':
    solve()