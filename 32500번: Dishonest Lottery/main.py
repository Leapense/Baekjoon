import sys

def main():
    input = sys.stdin.readline
    n_line = input().strip()
    if not n_line:
        return
    n = int(n_line)
    
    counts = [0] * 51
    total_lines = 10 * n
    
    for _ in range(total_lines):
        nums = list(map(int, input().split()))
        for x in nums:
            counts[x] += 1
            
    threshold = 2 * n
    suspicious = [i for i in range(1, 51) if counts[i] > threshold]
    
    if not suspicious:
        print(-1)
    else:
        print(" ".join(map(str, suspicious)))
        
if __name__ == "__main__":
    main()