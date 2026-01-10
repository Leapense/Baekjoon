import sys


def main():
    data = sys.stdin.buffer.read().split()
    if not data:
        return

    l = int(data[0])
    n = int(data[1])
    t = float(data[2])

    eps = 1e-9
    idx = 3
    for _ in range(n):
        f = float(data[idx])
        b = float(data[idx + 1])
        idx += 2

        total_time = l / f + l / b
        if total_time + eps < t:
            print("HOPE")
            return
    print("DOOMED")


if __name__ == "__main__":
    main()
