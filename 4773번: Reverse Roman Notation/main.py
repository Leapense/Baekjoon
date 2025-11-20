import sys

def roman_to_int(s: str) -> int:
    values = {
        'I': 1,
        'V': 5,
        'X': 10,
        'L': 50,
        'C': 100,
        'D': 500,
        'M': 1000,
    }
    
    total = 0
    prev = 0
    for ch in reversed(s):
        v = values[ch]
        if v < prev:
            total -= v
        else:
            total += v
            prev = v
    return total

def int_to_roman(n: int) -> str:
    thousands = ["", "M", "MM", "MMM", "MMMM"]
    hundreds = ["", "C", "CC", "CCC", "CD", "D",
                "DC", "DCC", "DCCC", "CM"]
    tens = ["", "X", "XX", "XXX", "XL", "L",
            "LX", "LXX", "LXXX", "XC"]
    ones = ["", "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX"]
    
    t = n // 1000
    h = (n % 1000) // 100
    te = (n % 100) // 10
    o = n % 10
    
    return thousands[t] + hundreds[h] + tens[te] + ones[o]

def main():
    stack = []
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        if line in "+-*/=":
            op = line
            if op == "=":
                if len(stack) < 1:
                    print("stack underflow")
                    continue
                
                value = stack[-1]
                if value <= 0 or value > 4999:
                    print("out of range exception")
                else:
                    print(int_to_roman(value))
            else:
                if len(stack) < 2:
                    print("stack underflow")
                    continue
                
                a = stack.pop()
                b = stack.pop()
                if op == "+":
                    stack.append(b + a)
                elif op == "-":
                    stack.append(b - a)
                elif op == "*":
                    stack.append(b * a)
                elif op == "/":
                    if a == 0:
                        print("division by zero exception")
                        stack.append(b)
                    else:
                        result = int(b / a)
                        stack.append(result)
                        
        else:
            value = roman_to_int(line)
            stack.append(value)
            
if __name__ == "__main__":
    main()