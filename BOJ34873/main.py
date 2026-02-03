import sys

def main():
    input = sys.stdin.readline
    N = int(input().strip())
    arr = list(map(int, input().split()))
    
    freq = [0] * (2 * N + 1)
    for x in arr:
        freq[x] += 1
        if freq[x] >= 3:
            print("No")
            return
        
    print("Yes")
    
if __name__ == "__main__":
    main()