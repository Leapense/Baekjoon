import sys
import math

def main():
    data = sys.stdin.buffer.read().split()
    if not data:
        return
    N = int(data[0])
    K = int(data[1])
    
    value = math.comb(N, K)
    ans = str(value).count('0')
    print(ans)
    
if __name__ == "__main__":
    main()