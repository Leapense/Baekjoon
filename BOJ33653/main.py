import sys

def main():
    input = sys.stdin.readline
    W = input().strip()
    _ = int(input().strip())
    S = input().rstrip('\n')
    
    count = 0
    start = 0
    while True:
        idx = S.find(W, start)
        if idx == -1:
            break
        count += 1
        start = idx + 1
        
    print(count)
    
if __name__ == "__main__":
    main()