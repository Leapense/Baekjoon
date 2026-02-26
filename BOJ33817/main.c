#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

#define MAX_N 1000000

static int cmp_int(const void *a, const void *b) {
    int x = *(const int*)a;
    int y = *(const int*)b;
    return (x > y) - (x < y);
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    // CWE-190 Mitigation: Validate range of n
    if (n <= 0 || n > MAX_N) {
        fprintf(stderr, "Error: Invalid input size.\n");
        return 1;
    }

    // CWE-190 Mitigation: Check for overflow before allocation
    if ((size_t)n > SIZE_MAX / sizeof(int) - 1) {
        return 1;
    }

    int *x = (int*)malloc((size_t)(n + 1) * sizeof(int));
    int *eligible = (int*)malloc((size_t)n * sizeof(int));

    if (!x || !eligible) {
        free(x);
        free(eligible);
        return 1;
    }

    int m = 0;
    for (int i = 1; i <= n; i++) {
        char s[8];
        int xi;
        if (scanf("%7s %d", s, &xi) != 2) {
            break;
        }

        x[i] = xi;
        if (strcmp(s, "TAK") == 0) {
            eligible[m++] = i;
        }
    }

    int ans[20];
    int acnt = 0;

    // CWE-125 Mitigation: Ensure we do not read past 'm'
    for (int i = 0; i < 10 && i < m; i++) {
        ans[acnt++] = eligible[i];
    }

    for (int i = 10; i < m && acnt < 20; i++) {
        int id = eligible[i];
        if (id >= 1 && id <= n && x[id] < 2) {
            ans[acnt++] = id;
        }
    }

    if (acnt > 0) {
        qsort(ans, (size_t)acnt, sizeof(int), cmp_int);
    }

    // CWE-457 Mitigation: Print only valid elements
    for (int i = 0; i < acnt; i++) {
        if (i) putchar(' ');
        printf("%d", ans[i]);
    }

    putchar('\n');

    free(x);
    free(eligible);
    return 0;
}