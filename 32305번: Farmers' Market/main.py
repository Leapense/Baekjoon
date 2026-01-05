import sys

def main():
    a, b = map(int, sys.stdin.readline().split())
    d = int(sys.stdin.readline())
    
    total = a * b
    dozens = (total + 11) // 12
    print(dozens * d)
    
if __name__ == "__main__":
    main()