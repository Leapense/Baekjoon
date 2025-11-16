import sys
import math

LIMIT = 1_000_000

def decompose_egyptian(m, n):
    a, b = m, n
    result = []
    
    while a > 0:
        start = (b + a - 1) // a
        chosen_d = None
        for d in range(start, b + 1):
            num = a * d - b
            den = b * d
            
            if num == 0:
                chosen_d = d
                result.append(d)
                a, b = 0, 1
                break
            
            g = math.gcd(num, den)
            new_b = den // g
            
            if new_b < LIMIT:
                chosen_d = d
                result.append(d)
                a = num // g
                b = new_b
                break
            
        if chosen_d is None:
            d = b
            num = a * d - b
            if num == 0:
                result.append(d)
                a, b = 0, 1
            else:
                den = b * d
                g = math.gcd(num, den)
                new_b = den // g
                result.append(d)
                a = num // g
                b = new_b
                
    return result

def main():
    input = sys.stdin.readline
    out_lines = []
    
    while True:
        line = input().strip()
        if not line:
            continue
        
        m, n = map(int, line.split())
        if m == 0 and n == 0:
            break
        
        ds = decompose_egyptian(m, n)
        out_lines.append(" ".join(map(str, ds)))
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()