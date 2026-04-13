import sys
from itertools import islice

def solve():
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    K = int(input_data[0])
    s = input_data[1]

    ans = 0
    cnt = 0

    for a, b in zip(s, islice(s, K, None)):
        if a == b:
            cnt += 1
        elif cnt:
            ans += cnt * (cnt + 1) // 2
            cnt = 0

    if cnt:
        ans += cnt * (cnt + 1) // 2

    print(ans, end='')

if __name__ == "__main__":
    solve()