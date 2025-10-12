import sys

def main():
    data = sys.stdin.read().strip().split()
    if not data:
        return
    N = int(data[0])

    if N % 2 == 0 or N % 5 == 0:
        print(-1)
        return

    rem = 0
    for length in range(1, N + 1):
        rem = (rem * 10 + 1) % N
        if rem == 0:
            print(length)
            return

    print(-1)

if __name__ == "__main__":
    main()
