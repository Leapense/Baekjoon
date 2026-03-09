import sys

def solve() -> None:
    input = sys.stdin.readline
    
    n = int(input())
    x = [0] * n
    r = [0] * n
    c = [0] * n
    
    for i in range(n):
        x[i], r[i], c[i] = map(int, input().split())
        
    answer = 1
    for i in range(n - 1):
        if x[i + 1] - x[i] > r[i] * c[i]:
            answer += 1
            
    print(answer, end='')
    
if __name__ == '__main__':
    solve()