#include <stdio.h>
#include <stdint.h>

static const int64_t MOD = 1000000007LL;
static const int64_t INV2 = 500000004LL;

static int64_t modpow(int64_t base, int64_t exp) {
    int64_t res = 1;
    while (exp) {
        if (exp & 1) res = (res * base) % MOD;
        base = (base * base) % MOD;
        exp >>= 1;
    }

    return res;
}

int main(void) {
    long long N;
    if (scanf("%lld", &N) != 1) return 0;

    long long p = modpow(26, N);
    long long q = modpow(22, N);
    long long ans = ((p + q) % MOD) * INV2 % MOD;

    printf("%lld\n", ans);
    return 0;
}