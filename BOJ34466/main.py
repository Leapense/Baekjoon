import sys

def main():
    A, B, X, Y = map(int, sys.stdin.readline().split())
    perim_side_by_side = 2 * ((A + X) + max(B, Y))
    perim_stacked = 2 * (max(A, X) + (B + Y))
    print(min(perim_side_by_side, perim_stacked), end='')
    
if __name__ == "__main__":
    main()