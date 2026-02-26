#include <stdio.h>
#include <stdint.h>

static uint64_t gcd_u64(uint64_t a, uint64_t b) {
    while (b != 0) {
        uint64_t t = a % b;
        a = b;
        b = t;
    }

    return a;
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    uint64_t l = 1;
    for (int k = 0; k < n; k++) {
        uint64_t a;
        scanf("%llu", (unsigned long long*)&a);

        uint64_t g = gcd_u64(l, a);
        __int128 tmp = (__int128)(l / g) * (__int128)a;
        l = (uint64_t)tmp;
    }

    printf("%llu\n", (unsigned long long)l);
    return 0;
}