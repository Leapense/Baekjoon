import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    idx = 0
    n = data[idx]; m = data[idx + 1]
    idx += 2
    k = data[idx]
    idx += 1
    selected = [False] * (n + 1)
    for _ in range(k):
        d = data[idx]
        idx += 1
        selected[d] = True
        
    union_mask = 0
    for disease in range(1, n + 1):
        p = data[idx]
        idx += 1
        if selected[disease]:
            mask = 0
            for _ in range(p):
                s = data[idx]
                idx += 1
                mask |= 1 << (s - 1)
            union_mask |= mask
        else:
            idx += p
            
    target_mask = (1 << m) - 1
    sys.stdout.write("yes" if union_mask == target_mask else "no")
    sys.stdout.write("\n")
    
if __name__ == "__main__":
    main()