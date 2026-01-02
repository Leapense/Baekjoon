y, c, p = map(int, input().split())
res = min(min(y, int(c / 2)),p)
print(res)