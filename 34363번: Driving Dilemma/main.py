import sys

SCALE = 10000

def parse_fixed_4(s: str) -> int:
    s = s.strip()
    if '.' in s:
        a, b = s.split('.', 1)
        b = (b + "0000")[:4]
    else:
        a, b = s, "0000"
    return int(a) * SCALE + int(b)
        
def main():
    input = sys.stdin.readline
    S = int(input().strip())
    D_int = parse_fixed_4(input())
    T_int = parse_fixed_4(input())
    
    left = S * 22 * T_int
    right = 15 * D_int
        
    if left >= right:
        print("MADE IT")
    else:
        print("FAILED TEST")
        
if __name__ == "__main__":
    main()