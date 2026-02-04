import sys

def main():
    input = sys.stdin.readline
    
    d = int(input().strip())
    a, o = map(int, input().split())
    da, do = map(int, input().split())
    
    A = a - d * da
    O = o - d * do
    
    if A < 0:
        A = 0
    if O < 0:
        O = 0
        
    total = A + O
    percent = 100.0 * (A / total)
    
    print("{:.15f}".format(percent).rstrip('0').rstrip('.') if percent != 0 else "0")
    
if __name__ == "__main__":
    main()