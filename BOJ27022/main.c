#include <stdio.h>
#include <stdlib.h>

static int cmp_ll(const void *a, const void *b) {
    long long x = *(const long long *)a;
    long long y = *(const long long *)b;
    if (x < y) return -1;
    if (x > y) return 1;
    return 0;
}

int main(void) {
    int D;
    if (scanf("%d", &D) != 1) return 0;

    long long x = 0, y = 0;
    long long *vertical = (long long *)malloc((size_t)D * sizeof(long long));
    long long *horizontal = (long long *)malloc((size_t)D * sizeof(long long));

    int vcnt = 0, hcnt = 0;
    for (int i = 0; i < D; ++i) {
        char dir;
        long long dist;
        scanf(" %c %lld", &dir, &dist);

        if (dir == 'N') {
            vertical[vcnt++] = x;
            y += dist;
        } else if (dir == 'S') {
            vertical[vcnt++] = x;
            y -= dist;
        } else if (dir == 'E') {
            horizontal[hcnt++] = y;
            x += dist;
        } else if (dir == 'W') {
            horizontal[hcnt++] = y;
            x -= dist;
        }
    }

    qsort(vertical, (size_t)vcnt, sizeof(long long), cmp_ll);
    qsort(horizontal, (size_t)hcnt, sizeof(long long), cmp_ll);

    int unique_v = 0, unique_h = 0;

    for (int i = 0; i < vcnt; ++i) {
        if (i == 0 || vertical[i] != vertical[i - 1]) {
            unique_v++;
        }
    }

    for (int i = 0; i < hcnt; ++i) {
        if (i == 0 || horizontal[i] != horizontal[i - 1]) {
            unique_h++;
        }
    }

    printf("%d", unique_v + unique_h);

    free(vertical);
    free(horizontal);

    return 0;
}