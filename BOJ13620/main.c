#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {
    int C, N;
    if (scanf("%d %d", &C, &N) != 2) {
        return 0;
    }

    long long L = C / N;
    long long *X = (long long *)malloc((size_t)N * sizeof(long long));
    long long *P = (long long *)malloc((size_t)(2 * N) * sizeof(long long));
    long long *B = (long long *)malloc((size_t)(2 * N) * sizeof(long long));
    int *maxdq = (int *)malloc((size_t)(2 * N) * sizeof(int));
    int *mindq = (int *)malloc((size_t)(2 * N) * sizeof(int));

    if (!X || !P || !B || !maxdq || !mindq) {
        free(X);
        free(P);
        free(B);
        free(maxdq);
        free(mindq);
        return 0;
    }

    for (int i = 0; i < N; ++i) {
        scanf("%lld", &X[i]);
    }

    for (int i = 0; i < 2 * N; ++i) {
        P[i] = X[i % N] + (i >= N ? C : 0);
        B[i] = P[i] - 1LL * i * L;
    }

    int max_front = 0, max_back = 0;
    int min_front = 0, min_back = 0;

    bool ok = false;

    for (int i = 0; i < 2 * N; ++i) {
        while (max_front < max_back && B[maxdq[max_back - 1]] <= B[i]) {
            --max_back;
        }
        maxdq[max_back++] = i;

        while (min_front < min_back && B[mindq[min_back - 1]] >= B[i]) {
            --min_back;
        }

        mindq[min_back++] = i;

        if (i >= N - 1) {
            int s = i - (N - 1);

            while (max_front < max_back && maxdq[max_front] < s) {
                ++max_front;
            }
            while (min_front < min_back && mindq[min_front] < s) {
                ++min_front;
            }

            if (s < N) {
                long long mx = B[maxdq[max_front]];
                long long mn = B[mindq[min_front]];
                if (mx - mn < L) {
                    ok = true;
                    break;
                }
            }
        }
    }

    printf("%c", ok ? 'S' : 'N');

    free(X);
    free(P);
    free(B);
    free(maxdq);
    free(mindq);

    return 0;
}