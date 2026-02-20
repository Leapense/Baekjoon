import sys

def main():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    l, r, x = map(int, input_data)
    seen = {i | x for i in range(l, r + 1)}
    ans = 0
    while True:
        if ans not in seen:
            print(ans)
            return
        ans += 1
    
if __name__ == "__main__":
    main()