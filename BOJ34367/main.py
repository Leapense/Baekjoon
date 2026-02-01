import sys

C = int(sys.stdin.readline().strip())
r = C % 9
print(9 if r == 0 else r)