N = int(input().strip())
res = []
for _ in range(N):
    subject_name, year = input().strip().split()
    if year == "2026":
        res.append(subject_name)

print(res[0])