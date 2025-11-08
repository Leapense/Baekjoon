import sys

def solve():
    data = sys.stdin.read().split()
    it = iter(data)
    out_lines = []
    
    while True:
        try:
            n = int(next(it))
        except StopIteration:
            break
        if n == 0:
            break
        
        s = next(it).strip()
        assert len(s) == n * (n + 1) // 2
        
        grid = []
        p = 0
        for r in range(n):
            row = list(s[p:p+r+1])
            p += r + 1
            grid.append(row)
            
        def in_bounds(x, y):
            return 0 <= x and 0 <= y and (x + y <= n - 1)
        
        def get_letter_xy(x, y):
            return grid[x + y][x]
        
        winners = set()
        
        for x in range(n):
            max_y = n - 1 - x
            for y in range(max_y + 1):
                a0 = get_letter_xy(x, y)
                for a in range(-n, n + 1):
                    for b in range(-n, n + 1):
                        if a == 0 and b == 0:
                            continue
                        bx, by = x + a, y + b
                        ra, rb = -b, a + b
                        cx, cy = x + ra, y + rb
                        if in_bounds(bx, by) and in_bounds(cx, cy):
                            if a0 == get_letter_xy(bx, by) == get_letter_xy(cx, cy):
                                winners.add(a0)
        
        out_lines.append("".join(sorted(winners)) if winners else "LOOOOOOOOSER!")
        
    print("\n".join(out_lines))
    
if __name__ == "__main__":
    solve()