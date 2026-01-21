import sys

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    arr = list(map(int, input().split()))
    mn = min(arr)
    mx = max(arr)
    print(mx - mn + 1)

if __name__ == "__main__":
    main()