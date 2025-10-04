import sys

def solve():
    sys.setrecursionlimit(1_000_000)
    s = sys.stdin.read().strip()
    s = s.replace('(', ' ( ').replace(')', ' ) ')
    raw_tokens = s.split()
    tokens = [t for t in raw_tokens if t != '.']
    
    i = 0
    
    def parse_value():
        nonlocal i
        tok = tokens[i]
        if tok == '(':
            i += 1
            return parse_expr()
        elif tok == ')':
            raise ValueError("Unexpected ')'")
        else:
            i += 1
            return int(tok)
        
    def parse_expr():
        nonlocal i 
        if i >= len(tokens):
            raise ValueError("Unexpected end of tokens")
        op = tokens[i]
        i += 1
        
        if op == '+':
            total = 0
            while i < len(tokens) and tokens[i] != ')':
                total += parse_value()
            if i >= len(tokens) or tokens[i] != ')':
                raise ValueError("Missing closing ')' for '+'")
            i += 1
            return total
        elif op == '-':
            a = parse_value()
            b = parse_value()
            if i >= len(tokens) or tokens[i] != ')':
                raise ValueError("Missing closing ')' for '-'")
            i += 1
            return a - b
        elif op == '/':
            a = parse_value()
            b = parse_value()
            if i >= len(tokens) or tokens[i] != ')':
                raise ValueError("Missing closing ')' for '/'")
            i += 1
            return a // b
        else:
            raise ValueError(f"Unknown operator: {op}")
        
    result = parse_value()
    print(result)
    
if __name__ == "__main__":
    solve()