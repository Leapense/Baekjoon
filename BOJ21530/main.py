import sys

def main():
    input = sys.stdin.readline
    N = int(input().strip())
    grid = [input().strip() for _ in range(N)]
    totC = sum(row.count('C') for row in grid)
    half = totC // 2
    order = []
    
    for r in range(N):
        if r % 2 == 0:
            for c in range(N):
                order.append((r, c))
        else:
            for c in range(N - 1, -1, -1):
                order.append((r, c))
    cnt = 0
    L = 0
    for i, (r, c) in enumerate(order):
        if grid[r][c] == 'C':
            cnt += 1
        if cnt == half:
            L = i + 1
            break
        
    ans = [['2'] * N for _ in range(N)]
    for i in range(L):
        r, c = order[i]
        ans[r][c] = '1'
        
    out = [''.join(row) for row in ans]
    print('\n'.join(out))
    
if __name__ == "__main__":
    main()