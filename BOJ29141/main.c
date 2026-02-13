#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <stdlib.h>

static int64_t i64abs_(int64_t x) { return x < 0 ? -x : x; }

int main(void) {
    int64_t xA, yA, xB, yB;
    int64_t a, b;

    if (scanf("%" SCNd64 " %" SCNd64, &xA, &yA) != 2) return 0;
    if (scanf("%" SCNd64 " %" SCNd64, &xB, &yB) != 2) return 0;
    if (scanf("%" SCNd64 " %" SCNd64, &a, &b) != 2) return 0;

    int64_t dx = i64abs_(xB - xA);
    int64_t dy = i64abs_(yB - yA);

    int64_t low = (dy + b - 1) / b;
    int64_t high = dx / a;

    if (high <= 0 || low > high) {
        puts("-1");
        return 0;
    }

    int64_t n = high;

    printf("%" PRId64 " %" PRId64 "\n", dx, n);
    printf("%" PRId64 " %" PRId64 "\n", dy, n);

    return 0;
}