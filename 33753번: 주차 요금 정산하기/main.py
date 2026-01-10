import sys


def main():
    A, B, C = map(int, sys.stdin.readline().split())
    T = int(sys.stdin.readline().strip())

    if T <= 30:
        print(A)
    else:
        excess = T - 30
        units = (excess + B - 1) // B
        print(A + units * C)


if __name__ == "__main__":
    main()
