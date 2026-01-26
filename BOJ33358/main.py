import sys
import datetime

def is_leap(y: int) -> bool:
    return (y % 400 == 0) or (y % 4 == 0 and y % 100 != 0)

def main():
    y = int(sys.stdin.readline().strip())
    days = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if is_leap(y):
        days[1] = 29
        
    total_rows = 0
    for m in range(1, 13):
        offset = datetime.date(y, m, 1).weekday()
        total_rows += (offset + days[m - 1] + 6) // 7
        
    print(total_rows)
    
if __name__ == "__main__":
    main()