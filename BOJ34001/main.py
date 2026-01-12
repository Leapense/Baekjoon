import sys


def required_monsters(L, quests):
    res = []
    for qlv, r1, r2 in quests:
        if L < qlv:
            res.append(0)
            continue
        cnt = 500
        if L >= r1:
            cnt -= 200
        if L >= r2:
            cnt -= 200
        res.append(cnt)
    return res


def main():
    L = int(sys.stdin.readline().strip())
    arcane = [
        (200, 210, 220),
        (210, 220, 225),
        (220, 225, 230),
        (225, 230, 235),
        (230, 235, 245),
        (235, 245, 250),
    ]

    grandis = [
        (260, 265, 270),
        (265, 270, 275),
        (270, 275, 280),
        (275, 280, 285),
        (280, 285, 290),
        (285, 290, 295),
        (290, 295, 300),
    ]

    a = required_monsters(L, arcane)
    g = required_monsters(L, grandis)

    print(" ".join(map(str, a)))
    print(" ".join(map(str, g)))


if __name__ == "__main__":
    main()
