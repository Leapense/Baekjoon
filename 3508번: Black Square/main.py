import sys

def main():
    data = sys.stdin.read().strip().splitlines()
    m, n, s, k = map(int, data[0].split())
    row = data[1].strip()
    
    T = m - s + 1
    if T <= 0:
        print("Impossible")
        return
    
    I_low = max(1, k - s + 1)
    I_high = min(k, T)
    I = max(0, I_high - I_low + 1)
    
    stars = [i + 1 for i, ch in enumerate(row) if ch == '*']
    
    if not stars:
        non_cover = T - I
        H = n - s + 1
        total = non_cover * H
        if total == 0:
            print("Impossible")
        elif total == 1:
            print("Unique")
        else:
            print("Ambiguous")
        return
    
    left = stars[0]
    right = stars[-1]
    L = right - left + 1
    contiguous = (len(stars) == L)
    if (not contiguous) or (L != s):
        print("Impossible")
        return
    
    if I == 0:
        print("Impossible")
    elif I == 1:
        print("Unique")
    else:
        print("Ambiguous")
    
if __name__ == "__main__":
    main()