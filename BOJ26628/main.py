import sys

def read_ints():
    data = sys.stdin.buffer.read()
    num = 0
    in_num = False
    for b in data:
        if 48 <= b <= 57:
            num = num * 10 + (b - 48)
            in_num = True
        elif in_num:
            yield num
            num = 0
            in_num = False
    if in_num:
        yield num
        
def solve():
    it = read_ints()
    n = next(it)
    
    MAX_A = 201718
    EXTRA = 25
    cnt = [0] * (MAX_A + EXTRA + 1)
    
    max_a = 0
    for _ in range(n):
        a = next(it)
        cnt[a] += 1
        if a > max_a:
            max_a = a
            
    ans = 0
    upper = max_a + 21
    
    for i in range(upper + 1):
        if cnt[i] & 1:
            ans = i
        cnt[i + 1] += cnt[i] >> 1
        
    print(ans)
    
if __name__ == '__main__':
    solve()