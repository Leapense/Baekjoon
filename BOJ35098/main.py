import sys


def prefix_function(s: str):
    n = len(s)
    pi = [0] * n
    j = 0

    for i in range(1, n):
        while j > 0 and s[i] != s[j]:
            j = pi[j - 1]

        if s[i] == s[j]:
            j += 1
            pi[i] = j
    return pi


def solve():
    input = sys.stdin.readline
    ans = []

    while True:
        line = input().strip()
        if line == "0":
            break

        n = int(line)
        s = input().strip()

        pi = prefix_function(s)
        overlap = pi[-1]
        ans.append(s + s[overlap:])

    print("\n".join(ans))


if __name__ == "__main__":
    solve()
