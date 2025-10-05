#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int *a = (int*)malloc(sizeof(int) * N);
    int maxV = 0;
    for (int i = 0; i < N; ++i) {
        scanf("%d", &a[i]);
        if (a[i] > maxV) maxV = a[i];
    }

    int *freq = (int*)calloc((size_t)maxV + 1, sizeof(int));
    int *divCount = (int*)calloc((size_t)maxV + 1, sizeof(int));
    if (!freq || !divCount) return 0;
    for (int i = 0; i < N; ++i) ++freq[a[i]];

    for (int d = 1; d <= maxV; ++d) {
        int fd = freq[d];
        if (fd == 0) continue;
        for (int m = d; m <= maxV; m += d) {
            divCount[m] += fd;
        }
    }

    for (int i = 0; i < N; ++i) {
        int ans = divCount[a[i]] - 1;
        printf("%d\n", ans);
    }

    free(a);
    free(freq);
    free(divCount);
    return 0;
}