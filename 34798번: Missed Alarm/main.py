t1 = input().strip()
t2 = input().strip()
h1, m1 = map(int, t1.split(':'))
h2, m2 = map(int, t2.split(':'))
total_m1 = h1 * 60 + m1
total_m2 = h2 * 60 + m2

if total_m1 >= total_m2:
    print("NO")
else:
    print("YES")