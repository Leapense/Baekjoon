import sys

SINGLE_DIGIT_RULE = "STRICT"

def smallest_number_with_digit_product(N: int) -> str | None:
    if N == 0:
        return "10"
    
    if 1 <= N <= 9:
        if SINGLE_DIGIT_RULE == "STRICT":
            return "1" + str(N)
        else:
            return str(N)
        
    digits = []
    n = N
    for d in range(9, 1, -1):
        while n % d == 0:
            digits.append(d)
            n //= d
            
    if n != 1:
        return None
    
    digits.sort()
    return "".join(map(str, digits))

def solve():
    out_lines = []
    for line in sys.stdin:
        s = line.strip()
        if not s:
            continue
        if s == "-1":
            break
        
        N = int(s)
        ans = smallest_number_with_digit_product(N)
        if ans is None:
            out_lines.append("There is no such number.")
        else:
            out_lines.append(ans)
            
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    solve()