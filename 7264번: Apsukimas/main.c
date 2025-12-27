#include <stdio.h>
#include <stdlib.h>

static inline int max_int(int a, int b) { return a > b ? a : b; }
static inline int min_int(int a, int b) { return a < b ? a : b; }

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    char *A = (char*)malloc((size_t)N + 5);
    char *B = (char*)malloc((size_t)N + 5);
    int *bad = (int*)malloc(((size_t)N + 1) * sizeof(int));
    if (!A || !B || !bad) return 0;

    scanf("%s", A);
    scanf("%s", B);

    int i0 = 1;
    while (i0 <= N && A[i0 - 1] == B[i0 - 1]) i0++;

    int j0 = N;
    while (j0 >= 1 && A[j0 - 1] == B[j0 - 1]) j0--;

    int K = i0 + j0;

    bad[0] = 0;
    for (int i = 1; i <= N; i++) {
        int j = K - i;
        int ok = (1 <= j && j <= N && B[i - 1] == A[j - 1]);
        bad[i] = bad[i - 1] + (ok ? 0 : 1);
    }

    int Lmin = max_int(1, K - N);
    int Lmax = min_int(i0, (K - 1) / 2);

    for (int l = Lmin; l <= Lmax; l++) {
        int r = K - l;
        if (r > N || r <= l) continue;
        if (bad[r] - bad[l - 1] == 0) {
            printf("%d %d\n", l, r);
            free(A);
            free(B);
            free(bad);
            return 0;
        }
    }

    free(A);
    free(B);
    free(bad);
    return 0;
}