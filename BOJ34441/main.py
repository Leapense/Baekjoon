import sys

def main():
    input = sys.stdin.readline
    hill_name = input().rstrip()
    time_str = input().rstrip()
    day = input().rstrip()
    bad_weather = int(input().rstrip())
    recently_snowed = int(input().rstrip())
    holiday = int(input().rstrip())
    
    h_str, m_str = time_str.split(':')
    base_minutes = int(h_str) * 60 + int(m_str)
    
    multiplier = 1
    if day in ("sat", "sun"):
        multiplier *= 2
    if bad_weather == 1:
        multiplier *= 2
    if recently_snowed == 1:
        multiplier *= 3
    if holiday == 1:
        multiplier *= 3
        
    total_minutes = base_minutes * multiplier
    out_h = total_minutes // 60
    out_m = total_minutes % 60
    
    print(f"{out_h}:{out_m:02d}")
    
if __name__ == "__main__":
    main()