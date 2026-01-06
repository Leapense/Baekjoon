import sys

def main():
    n = int(sys.stdin.readline().strip())
    ans = n * (n + 1) * (n + 2) // 6
    print(ans)
    
if __name__ == "__main__":
    main()