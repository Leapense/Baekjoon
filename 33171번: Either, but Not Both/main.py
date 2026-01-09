import math
import sys


def main():
    data = sys.stdin.read().strip().split()
    N = int(data[0])
    A = int(data[1])
    B = int(data[2])

    g = math.gcd(A, B)
    l = A * B // g

    count_a = N // A
    count_b = N // B
    count_both = N // l

    ans = count_a + count_b - 2 * count_both
    print(ans)


if __name__ == "__main__":
    main()
