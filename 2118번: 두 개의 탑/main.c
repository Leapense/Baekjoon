#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;
    int64_t *a = (int64_t*)malloc(sizeof(int64_t) * N);
    int64_t S = 0;
    for (int i = 0; i < N; ++i) {
        if (scanf("%lld", &a[i]) != 1) return 0;
        S += a[i];
    }

    int64_t *b = (int64_t*)malloc(sizeof(int64_t) * (2 * N));
    for (int i = 0; i < 2 * N; ++i) b[i] = a[i % N];
    int64_t half = S / 2;
    int64_t ans = 0, cur = 0;
    int j = 0;
    for (int i = 0; i < N; ++i) {
        while (j < i + N && cur + b[j] <= half) {
            cur += b[j];
            ++j;
        }
        if (cur > ans) ans = cur;
        cur -= b[i];
    }

    printf("%lld\n", ans);

    free(a);
    free(b);
    return 0;
}