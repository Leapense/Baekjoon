import sys

def eval_expr(expr: str) -> int:
    s = expr.replace(' ', '')
    total = 0
    sign = 1
    num = ""

    for ch in s:
        if ch.isdigit():
            num += ch
        else:
            total += sign * int(num)
            num = ""
            sign = 1 if ch == '+' else -1

    total += sign * int(num)
    return total

def solve_case(n: int):
    results = []

    def backtrack(pos: int, ops):
        if pos == n - 1:
            parts = ["1"]
            for i, op in enumerate(ops, start=2):
                parts.append(op)
                parts.append(str(i))

            expr = "".join(parts)

            if eval_expr(expr) == 0:
                results.append(expr)
            return

        for op in (' ', '+', '-'):
            ops.append(op)
            backtrack(pos + 1, ops)
            ops.pop()
    backtrack(0, [])
    return results

def main():
    input = sys.stdin.readline
    t = int(input().strip())
    out_lines = []
    for tc in range(t):
        n = int(input().strip())
        res = solve_case(n)
        out_lines.extend(res)
        if tc != t - 1:
            out_lines.append("")

    sys.stdout.write("\n".join(out_lines))

if __name__ == "__main__":
    main()
