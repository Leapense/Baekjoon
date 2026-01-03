import sys

def main():
    N, C = map(int, sys.stdin.readline().split())
    total = 1_000_000_000
    for _ in range(N):
        p, d, v = map(int, sys.stdin.readline().split())
        total = min(total, p + d + C * v)
    print(total)
    
if __name__ == "__main__":
    main()    