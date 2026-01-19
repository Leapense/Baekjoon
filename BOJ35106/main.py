import sys


def main():
    input = sys.stdin.readline
    N = int(input().strip())
    arr = list(map(int, input().split()))
    cnt = [0, 0, 0, 0]
    for x in arr:
        cnt[x] += 1

    original = None
    actual = None

    for v in (1, 2, 3):
        if cnt[v] == N - 1:
            original = v
        elif cnt[v] == N + 1:
            actual = v

    print(original)
    print(actual)


if __name__ == "__main__":
    main()
