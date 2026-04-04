import sys

s = sys.stdin.readline().strip()
count = [0] * 26
for ch in s:
    count[ord(ch) - ord("a")] += 1

odd = 0
for x in count:
    if x % 2 == 1:
        odd += 1

print("yes" if odd <= 1 else "no")
