#include <bits/stdc++.h>
using namespace std;

using u64 = unsigned long long;
using u128 = unsigned __int128;

static u128 isqrt_u128(u128 x) {
    u128 lo = 0, hi = (u128)1 << 64;
    while (lo < hi) {
        u128 mid = (lo + hi + 1) >> 1;
        if (mid * mid <= x) lo = mid;
        else hi = mid - 1;
    }
    return lo;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    u64 p, q;
    while ((cin >> p >> q)) {
        if (p == 0 && q == 0) break;
        if (p == 0) {
            cout << "0 2\n";
            continue;
        }
        u64 g = std::gcd(p, q);
        u64 pp = p / g, qq = q / g;

        bool found = false;
        for (u64 N = 2; N <= 50000; ++N) {
            u64 M = N * (N - 1);
            if (M % qq) continue;

            u64 t = M / qq;
            u128 s = (u128)pp * (u128)t;
            u128 D = (u128)1 + (u128)4 * s;
            u128 r = isqrt_u128(D);
            if (r * r != D) continue;

            u64 R = (u64)((r + 1) / 2);
            if (R > N) continue;
            cout << R << ' ' << (N - R) << '\n';
            found = true;

            break;
        }

        if (!found) cout << "impossible\n";
    }

    return 0;
}