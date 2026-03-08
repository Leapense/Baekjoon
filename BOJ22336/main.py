import sys

def solve():
    data = list(map(int, sys.stdin.read().split()))
    if not data:
        return

    n = data[0]
    a = data[1:]

    grid = [a[i * n:(i + 1) * n] for i in range(n)]

    rects = []

    # active[(L, R)] = start_row
    active = {}

    for r in range(1, n + 1):
        row = grid[r - 1]

        runs = []
        c = 1
        while c <= n:
            if row[c - 1] == 1:
                s = c
                while c <= n and row[c - 1] == 1:
                    c += 1
                runs.append((s, c - 1))
            else:
                c += 1

        cur = set(runs)

        # close intervals that do not continue on this row
        for seg in list(active.keys()):
            if seg not in cur:
                sr = active.pop(seg)
                L, R = seg
                rects.append((L, R, sr, r - 1))

        # open intervals that start on this row
        for seg in runs:
            if seg not in active:
                active[seg] = r

    # close remaining active intervals
    for seg, sr in active.items():
        L, R = seg
        rects.append((L, R, sr, n))

    print(len(rects))
    for L, R, T, B in rects:
        print(L, R, T, B)

if __name__ == "__main__":
    solve()