import sys

s = sys.stdin.readline().strip()
digits = {ch for ch in s if ch.isdigit()}
print(len(digits), end="")