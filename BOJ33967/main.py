import sys

def solve():
    input = sys.stdin.readline
    n = int(input().strip())
    s = input().strip()
    ans = 0
    for i in range(1, n):
        pair = s[i - 1] + s[i]
        if pair == "][":
            continue
        elif pair == "22" or pair == "55":
            ans += 2
        else:
            ans += 1
    print(ans, end='')

if __name__ == "__main__":
    solve()