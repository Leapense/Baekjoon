import sys

def is_single_bit(x):
    return x != 0 and (x & (x - 1)) == 0

def solve():
    input = sys.stdin.readline
    t = int(input().strip())
    out = []
    
    for _ in range(t):
        row1 = list(map(int, input().split()))
        row2 = list(map(int, input().split()))
        
        mask1 = [0] * 10
        mask2 = [0] * 10
        
        for c, v in enumerate(row1):
            mask1[v] |= 1 << c
        for c, v in enumerate(row2):
            mask2[v] |= 1 << c
            
        ok = False
        for v in range(1, 10):
            m1 = mask1[v]
            m2 = mask2[v]
            if m1 and m2:
                if not (m1 == m2 and is_single_bit(m1)):
                    ok = True
                    break
        out.append("YES" if ok else "NO")
        
    print("\n".join(out))
    
if __name__ == "__main__":
    solve()