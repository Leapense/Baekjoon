import sys


def main():
    input = sys.stdin.readline
    X, Y = map(int, input().split())
    N = int(input().strip())
    out = []
    for _ in range(N):
        x, y = map(int, input().split())
        if x == X or y == Y:
            out.append("0")
        else:
            out.append("1")

    print("\n".join(out))


if __name__ == "__main__":
    main()
