import sys

def main():
    n = int(sys.stdin.readline().strip())
    f0, f1 = 0, 1
    for _ in range(n + 1):
        f0, f1 = f1, f0 + f1
        
    print(f0)
    
if __name__ == "__main__":
    main()