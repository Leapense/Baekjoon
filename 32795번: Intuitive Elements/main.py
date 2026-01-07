import sys

def main():
    input = sys.stdin.readline
    t = int(input().strip())
    out_lines = []

    for _ in range(t):
        a = input().strip()
        b = input().strip()

        if set(b).issubset(set(a)):
            out_lines.append("YES")
        else:
            out_lines.append("NO")

    sys.stdout.write("\n".join(out_lines))

if __name__ == "__main__":
    main()