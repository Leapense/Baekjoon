#include <stdio.h>
#include <stdlib.h>

static int cmp_int(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    return (x > y) - (x < y);
}

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int *a = (int *)malloc((size_t)N * sizeof(int));
    if (!a) return 0;

    for (int i = 0; i < N; ++i) {
        scanf("%d", &a[i]);
    }

    qsort(a, (size_t)N, sizeof(int), cmp_int);
    int *ans = (int *)malloc(50000u * sizeof(int));
    if (!ans) {
        free(a);
        return 0;
    }

    int m = 0;
    int prev = a[0];

    for (int i = 1; i < N; ++i) {
        if (a[i] == prev) continue;

        for (int x = prev + 1; x < a[i]; ++x) {
            ans[m++] = x;
        }
        prev = a[i];
    }

    printf("%d\n", m);
    for (int i = 0; i < m; ++i) {
        if (i) putchar(' ');
        printf("%d", ans[i]);
    }

    putchar('\n');
    free(ans);
    free(a);

    return 0;
}