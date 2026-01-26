#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;
    int even_cnt = 0, odd_cnt = 0;

    int *a = (int*)malloc((size_t)N * sizeof(int));
    if (!a) return 0;

    for (int i = 0; i < N; i++) {
        scanf("%d", &a[i]);
        if (a[i] % 2 == 0) even_cnt++;
        else odd_cnt++;
    }

    bool target_even = (even_cnt > odd_cnt);
    int m = target_even ? even_cnt : odd_cnt;

    int limit = target_even ? 2 * (m + 1) : (2 * m + 1);

    bool *used = (bool*)calloc((size_t)limit + 1, sizeof(bool));
    if (!used) {
        free(a);
        return 0;
    }

    for (int i = 0; i < N; i++) {
        if (target_even) {
            if (a[i] % 2 == 0 && a[i] <= limit) used[a[i]] = true;
        } else {
            if (a[i] % 2 != 0 && a[i] <= limit) used[a[i]] = true;
        }
    }

    int start = target_even ? 2 : 1;
    for (int x = start; x <= limit; x += 2) {
        if (!used[x]) {
            printf("%d\n", x);
            break;
        }
    }

    free(used);
    free(a);
    return 0;
}