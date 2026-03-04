import sys

def main():
    h, m = map(int, sys.stdin.readline().split())
    if (12 * h - m) % 360 == 0:
        print("yes", end='')
    else:
        print("no", end='')
        
if __name__ == "__main__":
    main()