import sys

def worst_rank(n: int) -> int:
    if n <= 3:
        return 1
    if n == 4:
        return 2
    return n

def main():
    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        n = int(line)
        if n == 0:
            break
        print(worst_rank(n))

if __name__ == "__main__":
    main()