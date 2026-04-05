import sys


def solve():
    input = sys.stdin.readline
    n = int(input())
    t = list(map(int, input().split()))
    t.sort()

    cur = 0
    for x in t:
        cur = max(cur, x) + 1

    print(cur)


if __name__ == "__main__":
    solve()
