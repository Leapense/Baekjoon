import sys

def cell_to_idx(name: str) -> int:
    r = ord(name[0]) - ord('A')
    c = ord(name[1]) - ord('1')
    return r * 9 + c

def main():
    data = sys.stdin.read().split()
    n = 90

    is_literal = [False] * n
    literal_val = [0] * n
    deps = [[] for _ in range(n)]

    for i in range(n):
        tok = data[i]
        if tok.isdigit():
            is_literal[i] = True
            literal_val[i] = int(tok)
        else:
            parts = tok.split('+')
            deps[i] = [cell_to_idx(p) for p in parts]

    state = [0] * n
    value = [None] * n
    idx_in_stack = [-1] * n
    stack = []

    sys.setrecursionlimit(10000)

    def dfs(u: int):
        if state[u] == 2:
            return value[u]
        if state[u] == 1:
            pos = idx_in_stack[u]
            for node in stack[pos:]:
                value[node] = None
                state[node] = 2
            return None
        state[u] = 1
        idx_in_stack[u] = len(stack)
        stack.append(u)
        
        if is_literal[u]:
            val_u = literal_val[u]
        else:
            s = 0
            val_u = 0
            for v in deps[u]:
                vv = dfs(v)
                if state[u] == 2:
                    break
                if vv is None:
                    val_u = None
                    break
                s += vv
            else:
                val_u = s
                
        if state[u] != 2:
            value[u] = val_u
            state[u] = 2
        stack.pop()
        idx_in_stack[u] = -1
        return value[u]
    
    for i in range(n):
        if state[i] == 0:
            dfs(i)
    out_lines = []
    for r in range(10):
        row = []
        for c in range(9):
            idx = r * 9 + c
            if value[idx] is None:
                row.append('*')
            else:
                row.append(str(value[idx]))
        out_lines.append(' '.join(row))
        
    sys.stdout.write('\n'.join(out_lines))
    
if __name__ == "__main__":
    main()