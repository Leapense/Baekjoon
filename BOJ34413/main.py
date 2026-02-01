import sys

s = sys.stdin.readline().strip()
x_str, rest = s.split('d')
y_str, z_str = rest.split('+')

X = int(x_str)
Y = int(y_str)
Z = int(z_str)

numerator = X * (Y + 1) + 2 * Z

if numerator % 2 == 0:
    print(numerator // 2)
else:
    print(f"{numerator // 2}.5")
    