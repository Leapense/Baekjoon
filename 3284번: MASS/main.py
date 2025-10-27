import sys

def main():
    s = sys.stdin.readline().strip()
    mass = {'H': 1, 'C': 12, 'O': 16}
    stack = []
    
    i = 0
    n = len(s)
    while i < n:
        ch = s[i]
        if ch in mass:
            stack.append(mass[ch])
            i += 1
        elif ch == '(':
            stack.append('(')
            i += 1
        elif ch == ')':
            group_sum = 0
            while stack and stack[-1] != '(':
                group_sum += stack.pop()
            stack.pop()
            stack.append(group_sum)
            i += 1
        else:
            mul = int(ch)
            val = stack.pop()
            stack.append(val * mul)
            i += 1
    print(sum(x for x in stack if isinstance(x, int)))
    
if __name__ == "__main__":
    main()