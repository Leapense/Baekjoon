import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    if not data:
        return
    
    it = iter(data)
    T = next(it)
    N = next(it)
    
    masks = []
    for _ in range(N):
        z = next(it)
        m = 0
        for __ in range(z):
            x = next(it)
            m |= 1 << (x - 1)
        masks.append(m)
    
    masks = list(set(masks))
    
    masks.sort(key=lambda x: (x.bit_count(), x))
    kept = []
    for m in masks:
        redundant = False
        for k in kept:
            if (m & k) == k:
                redundant = True
                break
        if not redundant:
            kept.append(m)
            
    total_ok = 0
    ALL = 1 << T
    for s in range(ALL):
        bad = False
        for c in kept:
            if (s & c) == c:
                bad = True
                break
        if not bad:
            total_ok += 1
            
    print(total_ok)
    
if __name__ == "__main__":
    main()