import sys

START_HOUR_OFFSET = 7
WINDOW_DURATION = 10
TARGET_COUNT = 3

def to_minutes(time_str: str) -> int:
    h, mm = map(int, time_str.split(":"))
    return (h - START_HOUR_OFFSET) * 60 + mm

def solve():
    read_line = sys.stdin.readline
    try:
        line = read_line().strip()
        if not line:
            return
        n = int(line)
        
        times = sorted([to_minutes(read_line().strip()) for _ in range(n)])
    except ValueError:
        return
    
    max_events_in_window = 0
    right_ptr = 0
    
    for left_ptr in range(n):
        if right_ptr < left_ptr:
            right_ptr = left_ptr
            
        while right_ptr < n and times[right_ptr] - times[left_ptr] <= WINDOW_DURATION:
            right_ptr += 1
            
        current_window_size = right_ptr - left_ptr
        max_events_in_window = max(max_events_in_window, current_window_size)
        
    ans = max(0, TARGET_COUNT - max_events_in_window)
    print(ans)
    
if __name__ == "__main__":
    solve()