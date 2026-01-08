import math
import sys


def main():
    a, b = map(int, sys.stdin.readline().split())
    ans = math.hypot(a, b)
    print(f"{ans:.15f}")


if __name__ == "__main__":
    main()
