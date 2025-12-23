import sys

def trim_trailing_zeros(arr):
    """출력은 양의 정수만 있어야 하므로, 행 끝의 0들은 제거한다."""
    while arr and arr[-1] == 0:
        arr.pop()
    return arr

def rotate_left(rows):
    """
    왼쪽 회전: (x, y, z) -> (y, z, x)
    결과 SP의 '행'은 z(높이 레벨) 1..H, '열'은 기존의 y(행 인덱스) 1..n.
    각 칸 값 = 해당 (y, z)에서 가능한 x의 최대치 = 기존 행에서 (>= z)인 원소 개수.
    """
    n = len(rows)
    H = max(max(r) for r in rows)
    
    out = []
    for z in range(1, H + 1):
        line = []
        for i in range(n):
            cnt = 0
            for v in rows[i]:
                if v >= z:
                    cnt += 1
                else:
                    break
            line.append(cnt)
        trim_trailing_zeros(line)
        out.append(line)
    return out

def rotate_right(rows):
    """
    오른쪽 회전: (x, y, z) -> (z, x, y)
    결과 SP의 '행'은 기존 x(열 인덱스) 1..W, '열'은 기존 z(높이 레벨) 1..H.
    각 칸 값 = 해당 (x, z)에서 가능한 y의 최대치 = 그 열에서 값 >= z인 '행'의 개수.
    """
    H = max(max(r) for r in rows)
    W = max(len(r) for r in rows)
    
    out = []
    for x in range(1, W + 1):
        line = []
        for z in range(1, H + 1):
            cnt = 0
            for r in rows:
                if len(r) >= x and r[x - 1] >= z:
                    cnt += 1
                else:
                    pass
            line.append(cnt)
        trim_trailing_zeros(line)
        out.append(line)
    return out

def format_pattern(pat):
    return "\n".join(" ".join(map(str, row)) for row in pat)

def main():
    it = iter(map(int, sys.stdin.read().strip().split()))
    cases = []
    while True:
        try:
            n = next(it)
        except StopIteration:
            break
        if n == 0:
            break
        
        rows = []
        for _ in range(n):
            r = []
            while True:
                v = next(it)
                if v == 0:
                    break
                r.append(v)
            rows.append(r)
        L = rotate_left(rows)
        R = rotate_right(rows)
        cases.append(format_pattern(L) + "\n\n" + format_pattern(R))
    sys.stdout.write("\n\n\n".join(cases))
    if cases:
        sys.stdout.write("\n")

if __name__ == "__main__":
    main()