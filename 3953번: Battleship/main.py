import sys

def simulate_case(w, h, n, board1_lines, board2_lines, shots):
    boards = [board1_lines, board2_lines]
    ships = [sum(r.count('#') for r in boards[0]),
             sum(r.count('#') for r in boards[1])]

    visited = [
            [[False] * w for _ in range(h)],
            [[False] * w for _ in range(h)]
    ]

    p = 0
    turns_finished = [0, 0]

    def to_rc(x, y):
        return (h - 1 - y, x)

    i = 0
    while i < n:
        x, y = shots[i]
        i += 1

        q = 1 - p
        r, c = to_rc(x, y)

        if visited[q][r][c]:
            turns_finished[p] += 1
            if (ships[0] == 0 or ships[1] == 0) and (turns_finished[0] == turns_finished[1]):
                break
            p = q
            continue

        visited[q][r][c] = True
        if boards[q][r][c] == '#':
            ships[q] -= 1
            if ships[q] == 0:
                turns_finished[p] += 1
                if (ships[0] == 0 or ships[1] == 0) and (turns_finished[0] == turns_finished[1]):
                    break
                p = q
            else:
                pass
        else:
            turns_finished[p] += 1
            if (ships[0] == 0 or ships[1] == 0) and (turns_finished[0] == turns_finished[1]):
                break
            p = q

    if ships[0] == 0 and ships[1] == 0:
        return "draw"
    if ships[0] == 0 and ships[1] > 0:
        return "player two wins"
    if ships[1] == 0 and ships[0] > 0:
        return "player one wins"
    return "draw"

def main():
    data = sys.stdin.read().splitlines()
    it = iter(data)
    t = int(next(it).strip())
    outputs = []
    for _ in range(t):
        w, h, n = map(int, next(it).split())
        board1 = [next(it).rstrip('\n') for _ in range(h)]
        board2 = [next(it).rstrip('\n') for _ in range(h)]
        shots = [tuple(map(int, next(it).split())) for _ in range(n)]
        outputs.append(simulate_case(w, h, n, board1, board2, shots))

    print("\n".join(outputs))

if __name__ == "__main__":
    main()
