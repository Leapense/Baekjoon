import sys
from collections import defaultdict
from bisect import bisect_right

def is_subsequence(pos_map, pattern: str) -> bool:
    cur = -1
    for ch in pattern:
        lst = pos_map.get(ch)
        if not lst:
            return False
        i = bisect_right(lst, cur)
        if i == len(lst):
            return False
        cur = lst[i]
    return True

def main():
    data = sys.stdin.buffer.read().split()
    it = iter(data)
    
    N = int(next(it))
    M = int(next(it))
    names = [next(it).decode().casefold() for _ in range(N)]
    goods_raw = [next(it).decode().casefold() for _ in range(M)]
    goods = list(dict.fromkeys(goods_raw))
    out_lines = []
    for name in names:
        pos_map = defaultdict(list)
        for idx, ch in enumerate(name):
            pos_map[ch].append(idx)
            
        cnt = 0
        for g in goods:
            if len(g) <= len(name) and is_subsequence(pos_map, g):
                cnt += 1
        out_lines.append(str(cnt))
    sys.stdout.write("\n".join(out_lines))
    
if __name__ == "__main__":
    main()