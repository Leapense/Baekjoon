def all_rotations(t):
    rotations = []
    bottom_cases = [
        (t[0], [t[1], t[2], t[3]]),
        (t[1], [t[0], t[3], t[2]]),
        (t[2], [t[0], t[1], t[3]]),
        (t[3], [t[0], t[2], t[1]])
    ]
    for b, sides in bottom_cases:
        for i in range(3):
            r = [b] + sides[i:] + sides[:i]
            rotations.append(r)
    return rotations

K = int(input())
for _ in range(K):
    data = list(map(int, input().split()))
    A = data[:4]
    B = data[4:]
    same = any(rot == B for rot in all_rotations(A))
    print(1 if same else 0)