import sys

def main():
    input = sys.stdin.readline
    r, g, b = map(int, input().split())
    cr, cg, cb = map(int, input().split())
    c_rg, c_gb = map(int, input().split())
    
    dr = max(0, r - cr)
    dg = max(0, g - cg)
    db = max(0, b - cb)
    
    if dr > c_rg or db > c_gb:
        print(-1)
        return
    spare_green = (c_rg - dr) + (c_gb - db)
    if dg > spare_green:
        print(-1)
        return
    
    print(dr + dg + db)
    
if __name__ == "__main__":
    main()