#include <bits/stdc++.h>
using namespace std;
using int64 = long long;

static long long extgcd(long long a, long long b, long long &x, long long &y) {
    if (b == 0) { x = (a >= 0 ? 1 : -1); y = 0; return llabs(a); }
    long long x1, y1;
    long long g = extgcd(b, a % b, x1, y1);
    x = y1;
    y = x1 - (a / b) * y1;
    return g;
}

static long long modinv(long long a, long long m) {
    long long x, y;
    long long g = extgcd((a % m + m) % m, m, x, y);
    x %= m;
    if (x < 0) x += m;
    return x;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    long long L, R;
    long long k;
    if (!(cin >> L)) return 0;
    cin >> R >> k;

    const long long t = k * (k - 1) / 2;
    const long long g = std::gcd(t, k);
    const long long kp = k / g;
    const long long tp = t / g;
    long long inv = 0;
    bool trivial = (kp == 1);
    if (!trivial) {
        inv = modinv(tp % kp, kp);
    }

    auto add_mod = [](long long a, long long m) {
        long long r = a % m;
        if (r < 0) r += m;
        return r;
    };

    long long ans = 0;
    for (long long m = 0; m < kp; ++m) {
        long long rmod = g * m;
        long long dmin;
        if (trivial) {
            dmin = 1;
        } else if (m == 0) {
            dmin = kp;
        } else {
            dmin = (inv * m) % kp;
            if (dmin == 0) dmin = kp;
        }

        long long Smin = k + t * dmin;
        long long start = max(L, Smin);

        if (start > R) continue;

        long long first = start + add_mod(rmod - start, k);
        if (first > R) continue;

        long long cnt = (R - first) / k + 1;
        ans += cnt;
    }

    cout << ans << '\n';
    return 0;
}