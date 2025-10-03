import sys

def main():
    input = sys.stdin.readline
    N = int(input().strip())
    INF = 10 ** 30
    
    y_min = INF
    y_max = -INF
    max_y_plus_x = -INF
    max_y_minus_x = -INF
    min_y_plus_x = INF
    max_x_minus_y = -INF
    for _ in range(N):
        x, y = map(int, input().split())
        y_min = min(y_min, y)
        y_max = max(y_max, y)
        s1 = y + x
        s2 = y - x
        s3 = x - y
        max_y_plus_x = max(max_y_plus_x, s1)
        max_y_minus_x = max(max_y_minus_x, s2)
        min_y_plus_x = min(min_y_plus_x, s1)
        max_x_minus_y = max(max_x_minus_y, s3)
        
    L_up = (max_y_plus_x + max_y_minus_x) - 2 * y_min
    L_down = (-min_y_plus_x + max_x_minus_y) + 2 * y_max
    print(min(L_up, L_down))
    
if __name__ == "__main__":
    main()