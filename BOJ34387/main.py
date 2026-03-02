import sys

def main():
    input = sys.stdin.readline
    days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
    day_to_idx = {d: i for i, d in enumerate(days)}
    
    n_line = input().strip()
    if not n_line:
        return
    N = int(n_line)
    
    name_to_id = {}
    next_id = 0
    
    counts = [0] * 168
    seen_pairs = set()
    
    for _ in range(N):
        parts = input().split()
        name = parts[0]
        day = parts[1]
        x = int(parts[2])
        
        if name in name_to_id:
            sid = name_to_id[name]
        else:
            sid = next_id
            name_to_id[name] = sid
            next_id += 1
            
        base_slot = day_to_idx[day] * 24
        
        for i in range(3, 3 + x):
            hour = int(parts[i])
            slot = base_slot + hour
            key = sid * 168 + slot
            if key not in seen_pairs:
                seen_pairs.add(key)
                counts[slot] += 1
                
    best_slot = 0
    best_count = -1
    for s, c in enumerate(counts):
        if c > best_count:
            best_count = c
            best_slot = s
            
    best_day_idx = best_slot // 24
    best_hour = best_slot % 24
    
    print(f"Your professor should host office hours {days[best_day_idx]} @ {best_hour:02d}:00")
    
if __name__ == "__main__":
    main()