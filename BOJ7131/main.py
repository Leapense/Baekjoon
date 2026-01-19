import sys

def parse_cents(s: str) -> int:
    if '.' in s:
        a, b = s.split('.')
        b = (b + "00")[:2]
    else:
        a, b = s, "00"
    return int(a) * 100 + int(b)

def parse_margin_1e5(s: str) -> int:
    if '.' in s:
        a, b = s.split('.')
        b = (b + "00000")[:5]
    else:
        b = "00000"
    return int(b)

def round_half_up_fraction(num: int, den: int) -> int:
    return (2 * num + den) // (2 * den)

def main():
    input = sys.stdin.readline
    N, P0_str, T = input().split()
    N = int(N)
    T = int(T)
    P0 = parse_cents(P0_str)

    S = [0] * N
    I = [0] * N
    m1e5 = [0] * N

    for i in range(N):
        si, ii, mi = input().split()
        S[i] = int(si)
        I[i] = int(ii)
        m1e5[i] = parse_margin_1e5(mi)

    price = [0] * N
    active = [False] * N

    price[0] = P0
    active[0] = True

    for day in range(1, T):
        sum_snapshot = 0
        k_snapshot = 0
        for i in range(N):
            if active[i]:
                sum_snapshot += price[i]
                k_snapshot += 1

        new_price = price[:]
        for i in range(N):
            if day >= S[i] and (day - S[i]) % I[i] == 0:
                factor_num = 100000 + m1e5[i]
                den = k_snapshot * 100000
                num = sum_snapshot * factor_num
                np = round_half_up_fraction(num, den)
                new_price[i] = np

        price = new_price
        for i in range(N):
            if (not active[i]) and day == S[i]:
                active[i] = True

    out = []
    for i in range(N):
        out.append(f"{price[i] // 100}.{price[i] % 100:02d}")
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()