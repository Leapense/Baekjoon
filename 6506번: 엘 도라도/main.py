import sys

def count_increasing_subseq_len_k(a, k):
    n = len(a)
    if k == 1:
        return n
        
    dp_prev = [1] * n
    
    for length in range(2, k + 1):
        dp_curr = [0] * n
        for i in range(n):
            s = 0
            ai = a[i]
            for j in range(i):
                if a[j] < ai:
                    s += dp_prev[j]
            dp_curr[i] = s
        dp_prev = dp_curr
        
    return sum(dp_prev)
    
def main():
    data = sys.stdin.read().strip().split()
    it = iter(data)
    out_lines = []
    
    while True:
        n = int(next(it))
        k = int(next(it))
        if n == 0 and k == 0:
            break
            
        a = [int(next(it)) for _ in range(n)]
        out_lines.append(str(count_increasing_subseq_len_k(a, k)))
        
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()