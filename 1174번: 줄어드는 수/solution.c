#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <inttypes.h>

static int cmp_u64(const void *a, const void *b) {
    uint64_t x = *(const uint64_t*)a;
    uint64_t y = *(const uint64_t*)b;
    return (x > y) - (x < y);
}
int main(void) {
    long long N;
    if (scanf("%lld", &N) != 1) return 0;
    if (N > 1023) {
        puts("-1");
        return 0;
    }
    uint64_t arr[1023];
    int idx = 0;

    for (int mask = 1; mask < (1 << 10); ++mask) {
        uint64_t num = 0;
        for (int d = 9; d >= 0; --d) {
            if (mask & (1 << d)) num = num * 10u + (uint64_t)d;
        }
        arr[idx++] = num;
    }
    qsort(arr, idx, sizeof(uint64_t), cmp_u64);
    printf("%" PRIu64 "\n", arr[N - 1]);
    return 0;
}