import sys
from heapq import heappush, heappop
    
def precompute():
    MINV, MAXV = -65536, 65535
    OFF = -MINV
    N = MAXV - MINV + 1
    B = 10 ** 9
    INF = 10 ** 30
        
    CPLUS, CMINUS, INCR, DBL = 0, 1, 2, 3
    opname = ["C+1", "C-1", "INCR", "DBL"]
    dist = [INF] * N
    prev = [None] * N
    how = [-1] * N
    pq = []
    
    def push_start(val, op_code):
        idx = val + OFF
        dist[idx] = B
        prev[idx] = None
        how[idx] = op_code
        heappush(pq, (dist[idx], val))
        
    push_start(1, CPLUS)
    push_start(-1, CMINUS)
    
    while pq:
        d, x = heappop(pq)
        xi = x + OFF
        if d != dist[xi]:
            continue
            
        y = x + 1
        if MINV <= y <= MAXV:
            nd = d + B + 2
            yi = y + OFF
            if nd < dist[yi]:
                dist[yi] = nd
                prev[yi] = x
                how[yi] = INCR
                heappush(pq, (nd, y))
                
        y = x * 2
        if MINV <= y <= MAXV:
            nd = d + B + 1
            yi = y + OFF
            if nd < dist[yi]:
                dist[yi] = nd
                prev[yi] = x
                how[yi] = DBL
                heappush(pq, (nd, y))
                
    return OFF, prev, how, opname
    
def build_program(target, OFF, prev, how, opname):
    ops = []
    x = target
    while True:
        idx = x + OFF
        if how[idx] == -1:
            return ["C+1"]
        ops.append(opname[how[idx]])
        
        px = prev[idx]
        if px is None:
            break
        x = px
        
    ops.reverse()
    return ops
    
def main():
    data = sys.stdin.read().strip().split()
    nums = []
    for s in data:
        n = int(s)
        if n == 0:
            break
        nums.append(n)
        
    OFF, prev, how, opname = precompute()
    out_lines = []
    for i, n in enumerate(nums):
        out_lines.append(f"Constant {n}")
        out_lines.extend(build_program(n, OFF, prev, how, opname))
        if i != len(nums) - 1:
            out_lines.append("")
            
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()