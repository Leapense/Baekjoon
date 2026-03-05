import sys

def main():
    data = sys.stdin.read().split()
    if not data:
        return
    
    N = int(data[0])
    lines = data[1:1+N]
    
    enc = {'U': 0, 'R': 1, 'D': 2, 'L': 3}
    
    rows = [None] * (N + 1)
    for i, s in enumerate(lines):
        r = N - i
        rows[r] = [enc[ch] for ch in s]
        
    moves = []
    
    for r in range(1, N + 1):
        for c in range(len(rows[r])):
            cur = rows[r][c]
            t = (-cur) & 3
            
            for _ in range(t):
                moves.append((r, c + 1))
                
            rows[r][c] = 0
            if t and r < N:
                if c - 1 >= 0:
                    rows[r + 1][c - 1] = (rows[r + 1][c - 1] - t) & 3
                    
                if c < len(rows[r + 1]):
                    rows[r + 1][c] = (rows[r + 1][c] - t) & 3
                    
    out = sys.stdout
    for r, c in moves:
        out.write(f"{r} {c}\n")
        
if __name__ == "__main__":
    main()