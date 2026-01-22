import sys
from math import isqrt

def main():
    S_line = sys.stdin.readline().strip()
    if not S_line:
        return
    S = int(S_line)
    T = 2 * S
    ans = 0
    for a in range(1, isqrt(T) + 1):
        if T % a != 0:
            continue
        b = T // a
        c2 = a * a + b * b
        c = isqrt(c2)
        if c * c == c2:
            ans += 1
    print(ans)

if __name__ == "__main__":
    main()