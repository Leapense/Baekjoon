import sys

def solve():
    n = int(sys.stdin.readline())
    a = int(sys.stdin.readline())
    b = int(sys.stdin.readline())
    
    L = 2 * a
    R = 2 * b
    
    largest_multiple = (R // n) * n
    if largest_multiple >= L:
        S = largest_multiple
    else:
        S = L
        
    x = min(b, S - a)
    y = S - x
    print(x, y, end='')
    
if __name__ == "__main__":
    solve()