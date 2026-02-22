#include <stdio.h>

static unsigned long long n;

static int ok(unsigned long long h) {
    __uint128_t t = (__uint128_t)h * (h + 1) / 2;
    return t <= n;
}

int main(void) {
    if (scanf("%llu", &n) != 1) return 0;

    unsigned long long lo = 0, hi = 1;
    while (ok(hi)) {
        hi <<= 1;
    }

    while (lo + 1 < hi) {
        unsigned long long mid = lo + (hi - lo) / 2;
        if (ok(mid)) lo = mid;
        else hi = mid;
    }

    printf("%llu", lo);

    return 0;
}