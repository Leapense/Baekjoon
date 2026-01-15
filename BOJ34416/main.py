import sys

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    p = int(input().strip())
    m = int(input().strip())

    for _ in range(m):
        a, b = map(int,  input().split())
        if p == a:
            p = b
        elif p == b:
            p = a
        
    print(p, end='')

if __name__ == '__main__':
    main()