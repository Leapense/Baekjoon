import sys

def ints():
    for line in sys.stdin.buffer:
        s = line.strip()
        if s:
            yield int(s)
            
def main():
    it = ints()
    while True:
        try:
            x = next(it)
        except StopIteration:
            break
        
        L = x * 10_000_000
        
        try:
            n = next(it)
        except StopIteration:
            break
        
        arr = []
        for _ in range(n):
            try:
                arr.append(next(it))
            except StopIteration:
                arr = arr[:]
                break
            
        if n < 2:
            print("danger")
            continue
        
        arr.sort()
        i, j = 0, n - 1
        ans = None
        while i < j:
            s = arr[i] + arr[j]
            if s == L:
                ans = (arr[i], arr[j])
                break
            if s < L:
                i += 1
            else:
                j -= 1
                
        if ans is None:
            print("danger")
        else:
            print(f"yes {ans[0]} {ans[1]}")
            
if __name__ == "__main__":
    main()