import sys

def main():
    line = sys.stdin.readline().strip()
    if not line:
        return

    n_str, s = line.split()
    n = int(n_str)

    shift = 1
    out = []

    for i, ch in enumerate(s):
        x = ord(ch) - ord('A')
        y = (x + shift) % 26
        out.append(chr(y + ord('A')))
        shift = (shift * 2) % 26

    print("".join(out))

if __name__ == "__main__":
    main()