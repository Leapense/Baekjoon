import sys
from collections import deque

def solve():
    input = sys.stdin.readline
    T = int(input().strip())
    out_lines = []

    for _ in range(T):
        while True:
            line = input().strip()
            if line:
                break
        n, m, k = map(int, line.split())
        s = input().strip()
        assert len(s) == n

        limit = ord('a') + k
        def is_allowed(ch):
            oc = ord(ch)
            return ord('a') <= oc < limit

        nodes = [dict()]
        i = 0
        while i < n:
            if not is_allowed(s[i]):
                i += 1
                continue
            j = i
            while j < n and is_allowed(s[j]):
                j += 1
            for st in range(i, j):
                cur = 0
                end = min(j, st + m)
                for p in range(st, end):
                    c = ord(s[p]) - ord('a')
                    nxt = nodes[cur].get(c)
                    if nxt is None:
                        nxt = len(nodes)
                        nodes[cur][c] = nxt
                        nodes.append(dict())
                    cur = nxt
            i = j
        root = 0
        found = None
        for c in range(k):
            if c not in nodes[root]:
                found = chr(ord('a') + c)
                break
        if found is None:
            q = deque()
            for c in range(k):
                child = nodes[root].get(c)
                if child is not None:
                    q.append( (child, chr(ord('a') + c)) )

            while q:
                node, pref = q.popleft()
                d = len(pref)
                for c in range(k):
                    if c not in nodes[node]:
                        found = pref + chr(ord('a') + c)
                        q.clear()
                        break
                if found is not None:
                    break
                if d + 1 < m:
                    for c in range(k):
                        child = nodes[node].get(c)
                        if child is not None:
                            q.append( (child, pref + chr(ord('a') + c)) )

        out_lines.append(found)

    sys.stdout.write("\n".join(out_lines))

if __name__ == "__main__":
    solve()