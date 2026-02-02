import sys

def main():
    a, b, n, k = map(int, sys.stdin.readline().split())
    t = (k - 1) // n
    i = t // b + 1
    j = t % b + 1
    print(i, j)
    
if __name__ == "__main__":
    main()