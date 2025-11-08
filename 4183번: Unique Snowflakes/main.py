import sys

def solve():
    data = sys.stdin.buffer
    readline = data.readline

    t_line = readline()
    if not t_line:
        return
    t = int(t_line)

    out = []
    for _ in range(t):
        n = int(readline())
        last = {}
        L = 0
        best = 0

        for i in range(n):
            x = int(readline())
            prev = last.get(x)
            if prev is not None and prev >= L:
                L = prev + 1
            last[x] = i
            cur_len = i - L + 1
            if cur_len > best:
                best = cur_len

        out.append(str(best))

    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    solve()
