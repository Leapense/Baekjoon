import sys

s = sys.stdin.readline().strip()

if s.count('+') != 1:
    print("No Money", end="")
    sys.exit()

a, b = s.split('+')

if not a or not b or not a.isdigit() or not b.isdigit():
    print("No Money", end="")
    sys.exit()

if a[0] == '0':
    print("No Money", end="")
    sys.exit()

if a == b:
    print("CUTE", end="")
else:
    print("No Money", end="")