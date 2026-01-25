#include <stdio.h>
#include <stdint.h>

int main(void) {
    uint64_t R, K, M;
    if (scanf("%llu %llu %llu", &R, &K, &M) != 3) return 0;

    uint64_t h = M / K;
    uint64_t ans;

    if (h >= 64) ans = 0;
    else ans = R >> h;

    printf("%llu\n", ans);
    return 0;
}