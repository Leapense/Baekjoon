import sys

def better(a, b):
    if a[0] != b[0]:
        return a[0] > b[0]
    if a[1] != b[1]:
        return a[1] < b[1]
    if a[2] != b[2]:
        return a[2] < b[2]
    return a[3] < b[3]

def best_segment_nonempty(arr):
    cur_sum = arr[0]
    cur_len = 1
    cur_s = 1
    cur_e = 1
    cur = (cur_sum, cur_len, cur_s, cur_e)
    best = cur
    for i in range(2, len(arr) + 1):
        v = arr[i - 1]
        a_sum = cur[0] + v
        a_len = cur[1] + 1
        a_s = cur[2]
        a_e = i
        A = (a_sum, a_len, a_s, a_e)
        B = (v, 1, i, i)
        cur = A if better(A, B) else B
        if better(cur, best):
            best = cur
            
    return best[2], best[3], best[0]

def main():
    data = list(map(int, sys.stdin.buffer.read().split()))
    it = iter(data)
    n = next(it)
    
    total = 0
    ans = []
    
    for _ in range(n):
        L = next(it)
        row = [next(it) for _ in range(L)]
        s, e, val = best_segment_nonempty(row)
        total += val
        ans.append((s, e))
        
    out = [str(total)]
    out += [f"{s} {e}" for s, e in ans]
    print("\n".join(out))
    
if __name__ == "__main__":
    main()