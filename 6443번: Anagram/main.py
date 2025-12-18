import sys

def solve():
    input_data = sys.stdin.read().split()
    n = int(input_data[0])
    words = input_data[1:]
    sys.setrecursionlimit(1000000)
    out = sys.stdout.write

    for w in words:
        L = len(w)
        cnt = [0] * 26
        for ch in w:
            cnt[ord(ch) - 97] += 1

        path = [''] * L
        def dfs(depth: int):
            if depth == L:
                out(''.join(path) + '\n')
                return

            for i in range(26):
                if cnt[i] == 0:
                    continue
                cnt[i] -= 1
                path[depth] = chr(i + 97)
                dfs(depth + 1)
                cnt[i] += 1

        dfs(0)
if __name__ == "__main__":
    solve()