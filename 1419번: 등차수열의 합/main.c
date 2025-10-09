#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

static long long llabsll(long long x) { return x < 0 ? -x : x; }

static long long extgcd(long long a, long long b, long long *x, long long *y) {
    if (b == 0) {
        *x = (a >= 0 ? 1 : -1);
        *y = 0;
        return llabsll(a);
    }
    long long x1, y1;
    long long g = extgcd(b, a % b, &x1, &y1);
    *x = y1;
    *y = x1 - (a / b) * y1;
    return g;
}

static long long modinv(long long a, long long m) {
    long long x, y;
    extgcd((a % m + m) % m, m, &x, &y);
    x %= m;
    if (x < 0) x += m;
    return x;
}

static long long add_mod(long long a, long long m) {
    long long r = a % m;
    if (r < 0) r += m;
    return r;
}

static long long gcdll(long long a, long long b) {
    if (a < 0) a = -a;
    if (b < 0) b = -b;
    while (b) {
        long long t = a % b;
        a = b;
        b = t;
    }
    return a;
}

int main(void) {
    long long L, R, k;
    if (scanf("%lld", &L) != 1) return 0;
    scanf("%lld%lld", &R, &k);

    long long t = k * (k - 1) / 2;
    long long g = gcdll(t, k);
    long long kp = k / g;
    long long tp = t / g;

    long long inv = 0;
    int trivial = (kp == 1);
    if (!trivial) {
        inv = modinv(tp % kp, kp);
    }

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
        long long start = (L > Smin ? L : Smin);
        if (start > R) continue;

        long long first = start + add_mod(rmod - start, k);
        if (first > R) continue;
        long long cnt = (R - first) / k + 1;
        ans += cnt;
    }

    printf("%lld\n", ans);
    return 0;
}