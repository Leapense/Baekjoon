import sys
sys.setrecursionlimit(10**6)

def count_articulation_points(n, adj):
    disc = [0] * (n + 1)
    low = [0] * (n + 1)
    parent = [-1] * (n + 1)
    is_art = [False] * (n + 1)
    time = 0

    def dfs(u):
        nonlocal time
        time += 1
        disc[u] = low[u] = time
        children = 0

        for v in adj[u]:
            if disc[v] == 0:
                parent[v] = u
                children += 1
                dfs(v)
                low[u] = min(low[u], low[v])

                if parent[u] == -1 and children > 1:
                    is_art[u] = True

                if parent[u] != -1 and low[v] >= disc[u]:
                    is_art[u] = True

            elif v != parent[u]:
                low[u] = min(low[u], disc[v])
    for i in range(1, n + 1):
        if disc[i] == 0:
            dfs(i)

    return sum(is_art[1:])

def solve():
    input_lines = sys.stdin.read().splitlines()
    idx = 0
    out = []

    while idx < len(input_lines):
        line = input_lines[idx].strip()
        idx += 1
        if not line:
            continue

        n = int(line)
        if n == 0:
            break

        adj = [set() for _ in range(n + 1)]

        while idx < len(input_lines):
            s = input_lines[idx].strip()
            idx += 1
            if s == "0":
                break
            parts = list(map(int, s.split()))
            u = parts[0]
            for v in parts[1:]:
                if 1 <= u <= n and 1 <= v <= n and u != v:
                    adj[u].add(v)
                    adj[v].add(u)

        out.append(str(count_articulation_points(n, adj)))
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    solve()