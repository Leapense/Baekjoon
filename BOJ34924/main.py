import sys
input = sys.stdin.readline

N = int(input().strip())
S = input().strip()

p = S.index('P')
a = S.index('A')
u = S.index('U')
l = S.index('L')

if not (p < a < u < l):
    print('NO', end='')
    sys.exit(0)
    
segments = [
    p,
    a - p - 1,
    u - a - 1,
    l - u - 1,
    N - l - 1
]

if all(x % 2 == 0 for x in segments):
    print('YES', end='')
else:
    print('NO', end='')