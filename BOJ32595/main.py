import sys
from collections import deque

def main() -> None:
    data = sys.stdin.buffer.read().decode().split()
    if not data:
        return
    it = iter(data)
    
    n = int(next(it))
    m = int(next(it))
    r = int(next(it))
    
    pending = [deque() for _ in range(n)]
    
    for team_id in range(n):
        s = next(it)
        for j, ch in enumerate(s):
            if ch == 'P':
                pending[team_id].append(j)
                
    total_pending = sum(len(p) for p in pending)
    ranking = list(range(n))
    fav_id = r - 1
    bottom_ptr = n - 1
    for _ in range(total_pending):
        while bottom_ptr >= 0 and not pending[ranking[bottom_ptr]]:
            bottom_ptr -= 1
            
        cur_pos = bottom_ptr
        chosen = ranking[cur_pos]
        
        _ = next(it)
        chant = next(it)
        
        pending[chosen].popleft()
        if chant.startswith("Aaaw"):
            continue
        
        passed = chant.count('y') - 3
        if passed <= 0:
            continue
        
        new_pos = max(0, cur_pos - passed)
        if new_pos != cur_pos:
            ranking.pop(cur_pos)
            ranking.insert(new_pos, chosen)
    print(ranking.index(fav_id) + 1)
    
if __name__ == "__main__":
    main()