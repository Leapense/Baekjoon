#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int main(void) {
    int N;
    long long K;
    if (scanf("%d %lld", &N, &K) != 2) return 0;

    long long *A = (long long*)malloc(sizeof(long long) * (size_t)N);
    if (!A) return 0;

    long long maxA = LLONG_MIN, minA = LLONG_MAX;
    int idxMin = -1;

    for (int i = 0; i < N; ++i) {
        scanf("%lld", &A[i]);
        if (A[i] > maxA) maxA = A[i];
        if (A[i] < minA) { minA = A[i]; idxMin = i; }
    }

    if (maxA > K) {
        printf("0\n");
        free(A);
        return 0;
    }
    if (minA <= 0) {
        printf("-1\n");
        free(A);
        return 0;
    }

    long long m = minA;
    long long safe = 0;
    for (int i = 0; i < N; ++i) {
        if (i == idxMin) continue;
        long long cap = K - A[i];
        safe += cap / m;
    }

    long long ans = safe + 1;
    printf("%lld\n", ans);

    free(A);
    return 0;
}