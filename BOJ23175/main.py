import sys

def solve():
    data = sys.stdin.buffer.read()
    n = len(data)
    idx = 0
    
    def next_int():
        nonlocal idx
        while idx < n and data[idx] <= 32:
            idx += 1
        val = 0
        while idx < n and data[idx] > 32:
            val = val * 10 + (data[idx] - 48)
            idx += 1
        return val
    m = next_int()
    consumed = 0
    ans = []
    
    while consumed < m:
        x = next_int()
        consumed += 1
        ans.append(x)
        for _ in range(x - 1):
            v = next_int()
            consumed += 1
            
    sys.stdout.write(" ".join(map(str, ans)))
    
if __name__ == "__main__":
    solve()