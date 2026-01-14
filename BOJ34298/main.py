import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    R, S, N = data[0], data[1], data[2]
    A = data[3:3+N]
    
    if R == 0:
        print(0)
        return
    
    cur = 0
    for i, a in enumerate(A, start=1):
        cur = (cur + a) % S
        if cur == R:
            print(i)
            return
        
    print(-1)
    
if __name__ == '__main__':
    main()