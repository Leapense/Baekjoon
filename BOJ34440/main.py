import sys

def comb(n, r):
    if r > n - r:
        r = n - r
    result = 1
    for i in range(1, r + 1):
        result = result * (n - r + i) // i
    return result

def main():
    input = sys.stdin.readline
    n = int(input().strip())
    path = input().split()
    cnt_n = path.count('N')
    total_paths = comb(n, cnt_n)
    answer = total_paths - 1
    print(answer)

if __name__ == "__main__":
    main()
