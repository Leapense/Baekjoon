import sys
import math

def get_tokens():
    for line in sys.stdin:
        if len(line) > 1_000_000:
            sys.stderr.write("Error: Line length exceeded limit.\n")
            return
        for token in line.split():
            yield token
            
def solve():
    tokens = get_tokens()
    out = []
    
    while True:
        try:
            n_str = next(tokens)
            n = int(n_str)
        except StopIteration:
            break
        except ValueError:
            sys.stderr.write("Error: Invalid numeric input for n.\n")
            return 
        
        if n < 0 or n > 1_000_000:
            sys.stderr.write("Error: Number of ribbons out of bounds.\n")
            return
        
        if n == 0:
            out.append("0 0")
            continue
        
        usable = []
        try:
            for _ in range(n):
                val = int(next(tokens))
                usable.append(val - 2)
        except (StopIteration, ValueError):
            sys.stderr.write("Error: Malformed or incomplete ribbon data.\n")
            return
        
        if not usable:
            continue
        
        g = usable[0]
        for x in usable[1:]:
            g = math.gcd(g, x)
            
        if g == 0:
            total_bows = 0
        else:
            total_bows = sum(x // g for x in usable)
            
        out.append(f"{g} {total_bows}")
        
    sys.stdout.write("\n".join(out) + "\n")
    
if __name__ == "__main__":
    solve()