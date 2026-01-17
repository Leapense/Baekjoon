import sys


def main():
    N, M = map(int, sys.stdin.readline().split())
    if 100 * M >= 81 * N:
        print("yaho")
    else:
        print("no")


if __name__ == "__main__":
    main()
