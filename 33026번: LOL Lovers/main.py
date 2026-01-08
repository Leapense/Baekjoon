import sys


def main():
    input = sys.stdin.readline
    n = int(input().strip())
    s = input().strip()

    totalL = s.count("L")
    totalO = n - totalL

    prefL = 0
    prefO = 0

    for k in range(1, n):
        if s[k - 1] == "L":
            prefL += 1
        else:
            prefO += 1

        sufL = totalL - prefL
        sufO = totalO - prefO

        if prefL != sufL and prefO != sufO:
            print(k)
            return

    print(-1)


if __name__ == "__main__":
    main()
