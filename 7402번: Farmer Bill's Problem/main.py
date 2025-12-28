import sys

def intersects_or_touches(a, b):
    return (a[1] >= b[0] and b[1] >= a[0] and a[3] >= b[2] and b[3] >= a[2])

def merge(a, b):
    return (min(a[0], b[0]), max(a[1], b[1]), min(a[2], b[2]), max(a[3], b[3]))

def main():
    input = sys.stdin.readline
    x, y = map(int, input().split())
    n = int(input().strip())
    
    rects = []
    for _ in range(n):
        xi, yi, ri = map(int, input().split())
        rects.append((xi - ri, xi + ri, yi - ri, yi + ri))
    
    changed = True
    while changed:
        changed = False
        i = 0
        while i < len(rects):
            j = i + 1
            while j < len(rects):
                if intersects_or_touches(rects[i], rects[j]):
                    rects[i] = merge(rects[i], rects[j])
                    rects.pop(j)
                    changed = True
                else:
                    j += 1
            i += 1
    kept_area = 0
    for l, r, b, t in rects:
        kept_area += (r - l) * (t - b)
        
    total_area = x * y
    harvest_area = total_area - kept_area
    print(harvest_area)
    
if __name__ == "__main__":
    main()