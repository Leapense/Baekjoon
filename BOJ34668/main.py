import sys

input = sys.stdin.readline
Q = int(input())

START_HOUR = 6
START_MIN = 6
INTERVAL = 12

start_total = START_HOUR * 60 + START_MIN
last_today = 23 * 60 + 54

for _ in range(Q):
    M = int(input())
    depart_time = start_total + (M // 50) * INTERVAL
    if depart_time > last_today:
        print(-1)
    else:
        h = depart_time // 60
        m = depart_time % 60
        print(f"{h:02d}:{m:02d}")