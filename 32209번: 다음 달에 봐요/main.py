import sys

def main():
    input = sys.stdin.readline
    Q = int(input().strip())
    unused = 0

    for _ in range(Q):
        t, v = map(int, input().split())
        if t == 1:
            unused += v
        else:
            if unused < v:
                print("Adios")
                return
            unused -= v
    print("See you next month")

if __name__ == "__main__":
    main()
