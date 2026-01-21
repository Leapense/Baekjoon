import sys

def main():
    input = sys.stdin.readline
    n, k = map(int, input().split())
    ans = 0

    for _ in range(n):
        a, b, c = map(int, input().split())
        if k >= a and (k - a) % b == 0:
            ans += c

    print(ans)

if __name__ == "__main__":
    main()