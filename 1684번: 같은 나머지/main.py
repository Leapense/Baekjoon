import sys
from math import gcd

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    arr = list(map(int, input().split()))
    g = 0
    a0 = arr[0]
    for x in arr[1:]:
        g = gcd(g, abs(x - a0))
    print(g)

if __name__ == "__main__":
    main()
