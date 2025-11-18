import sys

def main():
    input = sys.stdin.readline
    while True:
        line = input().strip()
        if not line:
            continue
        n = int(line)
        if n == 0:
            break
        
        board = [list(input().strip()) for _ in range(n)]
        max_rooks = 0
        
        def safe(r, c):
            cc = c - 1
            while cc >= 0 and board[r][cc] != 'X':
                if board[r][cc] == 'R':
                    return False
                cc -= 1
            cc = c + 1
            while cc < n and board[r][cc] != 'X':
                if board[r][cc] == 'R':
                    return False
                cc += 1
                
            rr = r - 1
            while rr >= 0 and board[rr][c] != 'X':
                if board[rr][c] == 'R':
                    return False
                rr -= 1
                
            rr = r + 1
            while rr < n and board[rr][c] != 'X':
                if board[rr][c] == 'R':
                    return False
                rr += 1
                
            return True
        
        def dfs(idx, cnt):
            nonlocal max_rooks
            if idx == n * n:
                if cnt > max_rooks:
                    max_rooks = cnt
                return
            
            r = idx // n
            c = idx % n
            dfs(idx + 1, cnt)
            
            if board[r][c] == '.' and safe(r, c):
                board[r][c] = 'R'
                dfs(idx + 1, cnt + 1)
                board[r][c] = '.'
                
        dfs(0, 0)
        print(max_rooks)

if __name__ == "__main__":
    main()