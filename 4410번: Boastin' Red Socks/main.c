#include <stdio.h>
#include <stdint.h>

typedef unsigned long long u64;
typedef __uint128_t u128;

static u64 gcd_u64(u64 a, u64 b) {
    while (b) { u64 t = a % b; a = b; b = t; }
    return a;
}

static u128 isqrt_u128(u128 x) {
    u128 lo = 0, hi = (u128)1 << 64;
    while (lo < hi) {
        u128 mid = (lo + hi + 1) >> 1;
        if (mid * mid <= x) lo = mid;
        else hi = mid - 1;
    }
    return lo;
}

int main(void) {
    u64 p, q;
    while (scanf("%llu %llu", &p, &q) == 2) {
        if (p == 0 && q == 0) break;
        if (p == 0) {
            printf("0 2\n");
            continue;
        }

        u64 g = gcd_u64(p, q);
        u64 pp = p / g, qq = q / g;

        int found = 0;
        for (u64 N = 2; N <= 50000; ++N) {
            u64 M = N * (N - 1ULL);
            if (M % qq) continue;

            u64 t = M / qq;
            u128 s = (u128)pp * (u128)t;

            u128 D = (u128)1 + (u128)4 * s;
            u128 r = isqrt_u128(D);
            if (r * r != D) continue;

            u64 R = (u64)((r + 1) / 2);
            if (R > N) continue;

            printf("%llu %llu\n", R, (u64)(N - R));
            found = 1;
            break;
        }

        if (!found) puts("impossible");
    }

    return 0;
}