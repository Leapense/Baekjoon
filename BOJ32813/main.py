import sys

def solve():
    input = sys.stdin.readline
    r, c = map(int, input().split())
    grid = [input().strip() for _ in range(r)]
    
    directions = [
        (-1, -1), (-1, 0), (-1, 1),
        (0, -1),           (0, 1),
        (1, -1), (1, 0), (1, 1)
    ]
    
    locations = []
    
    for i in range(1, r - 1):
        for j in range(1, c - 1):
            if grid[i][j] != '0':
                continue
            
            ok = True
            for dx, dy in directions:
                ni, nj = i + dx, j + dy
                if grid[ni][nj] != 'O':
                    ok = False
                    break
            if ok:
                locations.append((i + 1, j + 1))
                
    if len(locations) == 0:
        print("Oh no!")
    elif len(locations) == 1:
        print(locations[0][0], locations[0][1])
    else:
        print(f"Oh no! {len(locations)} locations")
            
if __name__ == "__main__":
    solve()