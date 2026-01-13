import sys

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    N = data[0]
    arr = data[1:]
    first = [0] * (N + 1)
    ans = 0
    for i, x in enumerate(arr, 1):
        if first[x] == 0:
            first[x] = i
        else:
            ans = max(ans, i - first[x] - 1)
            
    print(ans)
    
if __name__ == "__main__":
    main()