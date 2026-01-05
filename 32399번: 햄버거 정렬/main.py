import sys

def reachable_in_one_move(s: str, target: str) -> bool:
    for i in range(3):
        c = s[i]
        rest = s[:i] + s[i+1:]
        for pos in range(3):
            ns = rest[:pos] + c + rest[pos:]
            if ns == target:
                return True
    return False

def main():
    s = sys.stdin.readline().strip()
    target = "(1)"
    if s == target:
        print(0, end = '')
    elif reachable_in_one_move(s, target):
        print(1, end = '')
    else:
        print(2, end = '')
        
if __name__ == "__main__":
    main()