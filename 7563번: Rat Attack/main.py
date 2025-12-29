import sys
from array import array

def solve():
    data = sys.stdin.buffer.read().split()
    it = iter(data)
    
    t = int(next(it))
    SIZE = 1025
    MAXC = 1024
    
    out_lines = []
    for _ in range(t):
        d = int(next(it))
        n = int(next(it))
        
        ps = [array('I', [0]) * (SIZE + 1) for _ in range(SIZE + 1)]
        for _ in range(n):
            x = int(next(it))
            y = int(next(it))
            s = int(next(it))
            ps[x + 1][y + 1] = s
        for x in range(1, SIZE + 1):
            row = ps[x]
            prev = ps[x - 1]
            acc = 0
            for y in range(1, SIZE + 1):
                acc += row[y]
                row[y] = acc + prev[y]
                
        xr = [(0, 0)] * SIZE
        yr = [(0, 0)] * SIZE
        for x in range(SIZE):
            x1 = x - d
            x2 = x + d
            if x1 < 0: x1 = 0
            if x2 > MAXC: x2 = MAXC
            xr[x] = (x1, x2)
        for y in range(SIZE):
            y1 = y - d
            y2 = y + d
            if y1 < 0: y1 = 0
            if y2 > MAXC: y2 = MAXC
            yr[y] = (y1, y2)
            
        best_sum = -1
        best_x = 0
        best_y = 0
        for x in range(SIZE):
            x1, x2 = xr[x]
            row_x2 = ps[x2 + 1]
            row_x1 = ps[x1]
            for y in range(SIZE):
                y1, y2 = yr[y]
                s = row_x2[y2 + 1] - row_x1[y2 + 1] - row_x2[y1] + row_x1[y1]
                if s > best_sum:
                    best_sum = s
                    best_x = x
                    best_y = y
        out_lines.append(f"{best_x} {best_y} {best_sum}")
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    solve()