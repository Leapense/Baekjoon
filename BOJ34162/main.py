import sys

def main():
    input = sys.stdin.readline
    K = int(input().strip())
    if K == 1:
        print(0, flush=True)
        print(1, flush=True)
        return
    print(1, flush=True)
    print(1, flush=True)
    x = int(input().strip())
    if x == 0:
        print(K, flush=True)
    else:
        print(x, flush=True)
        
if __name__ == "__main__":
    main()