import sys

SINGLE = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
SUB = {'IV': 4, 'IX': 9, 'XL': 40, 'XC': 90, 'CD': 400, 'CM': 900}

DESC = [
    (1000, 'M'), (900, 'CM'), (500, 'D'), (400, 'CD'), (100, 'C'), (90, 'XC'), (50, 'L'), (40, 'XL'), (10, 'X'), (9, 'IX'), (5, 'V'), (4, 'IV'), (1, 'I')
]

def roman_to_int(s: str) -> int:
    i = 0
    total = 0
    n = len(s)
    while i < n:
        if i + 1 < n and s[i:i+2] in SUB:
            total += SUB[s[i:i+2]]
            i += 2
        else:
            total += SINGLE[s[i]]
            i += 1
    return total

def int_to_roman(x: int) -> str:
    res = []
    for val, sym in DESC:
        cnt, x = divmod(x, val)
        if cnt:
            res.append(sym * cnt)
        if x == 0:
            break
    return ''.join(res)

def main():
    a = sys.stdin.readline().strip()
    b = sys.stdin.readline().strip()
    
    va = roman_to_int(a)
    vb = roman_to_int(b)
    s = va + vb
    
    print(s)
    print(int_to_roman(s))
    
if __name__ == "__main__":
    main()