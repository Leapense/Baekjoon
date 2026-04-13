#include <stdio.h>
#include <stdlib.h>

enum { NONE = -2 };
static int eval_result(int sa, int sb, int K, int used) {
    if (sa >= K && sb >= K) return used ? -1 : 1;
    if (sa - sb >= 50) return 1;
    if (sa >= K) return 1;
    if (sb >= K) return -1;
    return NONE;
}

int main(void) {
    int N, K;
    if (scanf("%d %d", &N, &K) != 2) return 0;

    int *A = (int *)malloc((size_t)(N + 1) * sizeof(int));
    int *B = (int *)malloc((size_t)(N + 1) * sizeof(int));
    if (A == NULL || B == NULL) {
        free(A);
        free(B);
        return 0;
    }

    int baseEnd = N + 1;
    int baseSA = 0, baseSB = 0;

    int sa = 0, sb = 0;
    for (int i = 1; i <= N; ++i) {
        scanf("%d %d", &A[i], &B[i]);
        sa += A[i];
        sb += B[i];

        if (baseEnd == N + 1) {
            int r = eval_result(sa, sb, K, 0);
            if (r != NONE) {
                baseEnd = i;
                baseSA = sa;
                baseSB = sb;
            }
        }
    }

    int answer;
    int futureUsedResult;

    if (baseEnd == N + 1) {
        answer = 0;
        futureUsedResult = 0;
    } else {
        answer = eval_result(baseSA, baseSB, K, 0);
        futureUsedResult = eval_result(baseSA, baseSB, K, 1);
    }

    sa = 0;
    sb = 0;
    
    int limit = (baseEnd == N + 1) ? N : baseEnd;

    for (int i = 1; i <= limit; ++i) {
        sa += A[i];
        sb += B[i];

        int d = A[i] / 2;
        int modSA = sa + d;
        int modSB = sb;

        int r = eval_result(modSA, modSB, K, 1);

        if (r == NONE) {
            r = futureUsedResult;
        }
        if (r > answer) answer = r;
    }

    printf("%d", answer);

    free(A);
    free(B);
    return 0;
}