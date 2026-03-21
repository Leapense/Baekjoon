import sys

def solve():
    s = sys.stdin.readline().strip()
    t = sys.stdin.readline().strip()
    cnt_s = [0] * 26
    cnt_t = [0] * 26
    for ch in s:
        cnt_s[ord(ch) - ord('A')] += 1

    for ch in t:
        cnt_t[ord(ch) - ord('A')] += 1

    for i in range(26):
        if cnt_t[i] > cnt_s[i]:
            print("NEED FIX")
            return

    print("OK")

if __name__ == "__main__":
    solve()
