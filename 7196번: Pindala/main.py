import sys

def main():
    input = sys.stdin.readline
    
    K = int(input().strip())
    s = input().strip()
    
    x = y = 0
    area2 = 0
    
    for c in s:
        nx, ny = x, y
        if c == 'N':
            ny += 1
        elif c == 'S':
            ny -= 1
        elif c == 'E':
            nx += 1
        else:
            nx -= 1
            
        area2 += x * ny - nx * y
        x, y = nx, ny
        
    print(abs(area2) // 2)
    
if __name__ == "__main__":
    main()