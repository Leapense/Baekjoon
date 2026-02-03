import sys

def solve():
    data = sys.stdin.buffer.read().split()
    t = int(data[0])
    idx = 1
    out = []
    
    for _ in range(t):
        m = int(data[idx]); idx += 1
        ans = 0
        while m > 1:
            ans += (m & 1)
            m = (m + 1) // 2
        out.append(str(ans))
    sys.stdout.write("\n".join(out))
    
if __name__ == "__main__":
    solve()