import sys
def main():
    k = int(sys.stdin.readline().strip())
    b = k // 5
    r = k % 5
    
    total = 30 * (5 * (b * (b + 1) // 2) + r * (b + 1))
    print(total)
    
if __name__ == "__main__":
    main()