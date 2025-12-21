import sys

def main():
    data = sys.stdin.read().strip().split()
    W, H, cutW, cutH, steps = map(int, data)

    def inside(x: int, y: int) -> bool:
        if not (1 <= x <= W and 1 <= y <= H):
            return False
        if x <= cutW and y <= cutH:
            return False
        if x > W - cutW and y <= cutH:
            return False
        if x <= cutW and y > H - cutH:
            return False
        if x > W - cutW and y > H - cutH:
            return False
        return True

    start_x = None
    for x in range(1, W + 1):
        if inside(x, 1):
            start_x = x
            break
    
    if start_x is None:
        return

    x, y = start_x, 1

    dirs = [(1, 0), (0, 1), (-1, 0), (0, -1)]
    d = 0
    visited = {(x, y)}

    for _ in range(steps):
        moved = False

        for nd in ((d + 3) % 4, d, (d + 1) % 4, (d + 2) % 4):
            nx = x + dirs[nd][0]
            ny = y + dirs[nd][1]
            if inside(nx, ny) and (nx, ny) not in visited:
                x, y = nx, ny
                d = nd
                visited.add((x, y))
                moved = True
                break

        if not moved:
            break

    print(x)
    print(y)

if __name__ == "__main__":
    main()