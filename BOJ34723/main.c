#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

static inline int64_t i64abs(int64_t x) { return x < 0 ? -x : x; }

int main(void) {
    int P, M, C;
    int64_t X;

    if (scanf("%d %d %d", &P, &M, &C) != 3) return 0;
    if (scanf("%" SCNd64, &X) != 1) return 0;

    int64_t ans = (int64_t)9e18;

    for (int p = 1; p <= P; ++p) {
        for (int m = 1; m <= M; ++m) {
            for (int c = 1; c <= C; ++c) {
                int64_t v = (int64_t)(p + m) * (int64_t)(m + c);
                int64_t diff = i64abs(v - X);
                if (diff < ans) ans = diff;
            }
        }
    }

    printf("%" PRId64 "\n", ans);
    return 0;
}
