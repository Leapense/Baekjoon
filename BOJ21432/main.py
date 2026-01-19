import sys

def main():
    input = sys.stdin.readline
    n, a, b = map(int, input().split())
    A = []
    B = []
    for _ in range(n):
        ai, bi = map(int, input().split())
        A.append(ai)
        B.append(bi)
        
    ans = 0.0
    for i in range(-1, n):
        for j in range(-1, n):
            if i != -1 and j != -1 and i == j:
                continue
            total = 0.0
            if i != -1 and a > 0:
                total += a / A[i]
            if j != -1 and b > 0:
                total += b / B[j]
                
            if total > ans:
                ans = total
                
    print(f"{ans:.2f}")
    
if __name__ == "__main__":
    main()