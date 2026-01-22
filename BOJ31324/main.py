import sys

def main():
    data = sys.stdin.readline().strip().split()
    N = int(data[0])
    M = int(data[1])

    cap = M if M > 0 else 0
    W = 1
    for i in range(1, N + 1):
        W *= 2 * i
        if cap and W >= cap:
            W = cap
            break

    if W >= M:
        print("Harshat Mata")
    else:
        print("Nope")

if __name__ == "__main__":
    main()