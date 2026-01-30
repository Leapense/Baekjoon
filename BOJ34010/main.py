import sys
def main():
    input = sys.stdin.buffer.readline
    N_line = input()
    if not N_line:
        return
    N = int(N_line)
    for _ in range(2 * N - 1):
        input()
        
    dist = 2 * (N - 1)
    turns = dist - 1
    sys.stdout.write(f"{dist} {turns}\n")
    
if __name__ == "__main__":
    main()