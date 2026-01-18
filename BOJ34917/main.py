import sys


def build_m(n: int):
    mid = n // 2
    lines = []
    for r in range(n):
        row = ["."] * n
        row[0] = "#"
        row[n - 1] = "#"

        if r <= mid:
            row[r] = "#"
            row[n - 1 - r] = "#"

        lines.append("".join(row))
    return lines


def main():
    input = sys.stdin.readline
    t = int(input().strip())
    out = []
    for _ in range(t):
        n = int(input().strip())
        out.extend(build_m(n))
    sys.stdout.write("\n".join(out))


if __name__ == "__main__":
    main()
