import sys

input = sys.stdin.readline

T = int(input())

for tc in range(1, T + 1):
    sx, sy = map(int, input().split())
    path = input().strip()
    bitmap = [['.' for _ in range(32)] for _ in range(32)]
    
    x, y = sx, sy
    for ch in path:
        if ch == '.':
            break
        
        if ch == 'E':
            bitmap[y - 1][x] = 'X'
            x += 1
        elif ch == 'W':
            bitmap[y][x - 1] = 'X'
            x -= 1
        elif ch == 'N':
            bitmap[y][x] = 'X'
            y += 1
        elif ch == 'S':
            bitmap[y - 1][x - 1] = 'X'
            y -= 1
        
    print(f"Bitmap #{tc}")
    for row in range(31, -1, -1):
        print(''.join(bitmap[row]))