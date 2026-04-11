import sys
import math

input = sys.stdin.readline

def solve():
    n = int(input().strip())
    a, b = map(float, input().split())
    px, py = map(int, input().split())

    eps = 1e-12

    for _ in range(n):
        qx, qy = map(int, input().split())
        dx = qx - px
        dy = qy - py

        dist = math.hypot(dx, dy)

        if a - eps <= dist <= b + eps:
            angle = math.atan2(dy, dx)
            print(f"{qx} {qy} {dist:.8f} {angle:.8f}", end='')
            return

    print("impossible", end='')

if __name__ == "__main__":
    solve()