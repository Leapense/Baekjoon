#include <bits/stdc++.h>
using i64 = long long;

constexpr i64 mod360(i64 x) {
    x %= 360;
    if (x < 0) x += 360;
    return x;
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    i64 H, M, N;
    std::cin >> H >> M >> N;

    i64 h = 30 * H;
    i64 m = 6 * M;

    i64 r = mod360(m - h);
    i64 t1 = (360 - r) / 6;

    if (N < t1) {
        m = mod360(m + 12 * N);
        h = mod360(h + 6 * N);
    } else {
        N -= t1;
        h = mod360(h + 6 * t1);
        m = h;
        int dir = -1;

        i64 cycles = N / 80;
        i64 inc = (120 * cycles) % 360;
        h = mod360(h + inc);
        m = h;
        N %= 80;

        while (N > 0) {
            i64 seg = (dir == -1 ? 20 : 60);
            i64 step = std::min<i64>(seg, N);
            m = mod360(m + dir * 12 * step);
            h = mod360(h + 6 * step);
            N -= step;
            if (step == seg) dir = -dir;
        }
    }

    std::cout << (h / 30) % 12 << ' ' << (m / 6) % 60 << '\n';
    return 0;
}