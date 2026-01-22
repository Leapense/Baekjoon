import sys

def main():
    input_data = sys.stdin.readline().strip().split()
    N, d, ap1, ap2 = map(int, input_data)
    planes = [
        {"pos": 0, "dir": 1, "ap": ap1, "vis": [False] * N, "cnt": 1, "dist": 0},
        {"pos": 0, "dir": -1, "ap": ap2, "vis": [False] * N, "cnt": 1, "dist": 0},
    ]
    planes[0]["vis"][0] = True
    planes[1]["vis"][0] = True

    meet = 1
    cross = 0

    turn = 0
    while planes[0]["cnt"] < N or planes[1]["cnt"] < N:
        p = planes[turn]
        o = planes[1 - turn]

        if p["cnt"] < N:
            k = 0
            pos = p["pos"]
            steps = 0
            while True:
                pos = (pos + p["dir"]) % N
                steps += 1
                if k < p["ap"]:
                    if pos == o["pos"]:
                        cross += 1
                    k += 1
                    continue
                if not p["vis"][pos]:
                    if pos == o["pos"]:
                        meet += 1
                    break
                else:
                    if pos == o["pos"]:
                        cross += 1

            p["dist"] += steps * d
            p["pos"] = pos
            p["vis"][pos] = True
            p["cnt"] += 1

        turn = 1 - turn

    print(planes[0]["dist"], planes[1]["dist"], meet, cross)

if __name__ == "__main__":
    main()