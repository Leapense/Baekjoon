import sys

def solve():
    input = sys.stdin.readline
    n, p = map(int, input().split())
    for _ in range(n):
        input()
        
    m = n - 1
    A = m // 3 + (1 if m % 3 >= 1 else 0)
    B = m // 3 + (1 if m % 3 >= 2 else 0)
    
    if (p - 1) >= B and (n - p) >= A:
        print("YES")
    else:
        print("NO")
        
if __name__ == "__main__":
    solve()