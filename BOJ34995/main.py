import sys

input = sys.stdin.readline

N, s = input().split()
N = int(N)
A = input().strip()

if N > len(A):
    ans = ''.join('1' if c == '?' else c for c in s)
    print(ans)
    sys.exit(0)
    
if N < len(A):
    print(-1)
    sys.exit(0)
    
n = N

good = [False] * (n + 1)
good[n] = True

for i in range(n - 1, -1, -1):
    target = ord(A[i]) - ord('0')
    c = s[i]
    
    if c == '?':
        possible = False
        for d in range(1, 10):
            if d > target:
                possible = True
                break
            if d == target and good[i + 1]:
                possible = True
                break
        good[i] = possible
    else:
        d = ord(c) - ord('0')
        if d > target:
            good[i] = True
        elif d == target and good[i + 1]:
            good[i] = True
        else:
            good[i] = False
            
if not good[0]:
    print(-1)
    sys.exit(0)
    
ans = []
already_greater = False

for i in range(n):
    c = s[i]
    if already_greater:
        if c == '?':
            ans.append('1')
        else:
            ans.append(c)
        continue
    target = ord(A[i]) - ord('0')
    if c == '?':
        digits = range(1, 10)
    else:
        digits = [ord(c) - ord('0')]
        
    if target in digits and good[i + 1]:
        ans.append(str(target))
        continue
    
    chosen = -1
    for d in digits:
        if d > target:
            chosen = d
            break
        
    if chosen == -1:
        print(-1, end='')
        sys.exit(0)
        
    ans.append(str(chosen))
    already_greater = True
    
print(''.join(ans), end='')