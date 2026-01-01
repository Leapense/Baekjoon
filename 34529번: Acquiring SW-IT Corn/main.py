import sys

def main():
    X, Y, Z = map(int, sys.stdin.readline().split())
    U, V, W = map(int, sys.stdin.readline().split())
    total = (U // 100) * X + (V // 50) * Y + (W // 20) * Z
    print(total)
    
if __name__ == "__main__":
    main()