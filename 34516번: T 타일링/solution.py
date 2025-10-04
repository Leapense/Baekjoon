import sys

def main() -> None:
    N = int(sys.stdin.readline().strip())
    if N % 4:
        print(-1)
        return
    
    pat = ["aaab","dabb","ddcb","dccc"]
    out_lines = [
        ''.join(pat[i & 3][j & 3] for j in range(N))
        for i in range(N)
    ]
    print('\n'.join(out_lines))
    
if __name__ == "__main__":
    main()