import sys

def main():
    n = int(sys.stdin.readline().strip())
    ans = [0] * (n + 1)
    for i in range(n, 0, -1):
        if ans[i] != 0:
            continue
        S = 1 << i.bit_length()
        j = S - i

        ans[i] = j
        ans[j] = i

    print("\n".join(str(ans[i]) for i in range(1, n + 1)))

if __name__ == "__main__":
    main()
