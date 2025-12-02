import sys
def solve():
    data = list(map(int, sys.stdin.read().split()))
    out_lines = []
    i = 0
    L = len(data)
    
    while i + 2 < L:
        num_schools = data[i]; i += 1
        target_school = data[i]; i += 1
        num_booklets = data[i]; i += 1
        
        pages = data[i:i+num_booklets]
        i += num_booklets
        
        S = num_schools
        K = target_school
        N = num_booklets
        
        q, r = divmod(N, S)
        cnt = [q + 1 if s < r else q for s in range(S)]
        items = [(pages[idx], idx) for idx in range(N)]
        items.sort(key=lambda x: (x[0], x[1]))
        
        school_of = [-1] * N
        pos = 0
        for s in range(S):
            for _ in range(cnt[s]):
                page_val, orig_idx = items[pos]
                pos += 1
                school_of[orig_idx] = s
                
        first_index = None
        for idx in range(N):
            if school_of[idx] == K:
                first_index = idx
                break
        answer = pages[first_index]
        out_lines.append(str(answer))
        
    sys.stdout.write("\n".join(out_lines))

if __name__ == "__main__":
    solve()