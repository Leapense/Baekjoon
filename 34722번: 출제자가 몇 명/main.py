N = int(input().strip())
cnt = 0
for _ in range(N):
    s, c, a, r = map(int, input().split())
    if s >= 1000 or c >= 1600 or a >= 1500 or 0 < r <= 30:
        cnt += 1

print(cnt)