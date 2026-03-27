#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) {
        return 0;
    }

    int *A = (int *)malloc((N + 1) * sizeof(int));
    int *B = (int *)malloc((2 * N + 1) * sizeof(int));
    bool *exists = (bool *)calloc((N + 1), sizeof(bool));

    if (A == NULL || B == NULL || exists == NULL) {
        free(A);
        free(B);
        free(exists);
        return 0;
    }

    for (int i = 1; i <= N; ++i) {
        scanf("%d", &A[i]);
        exists[A[i]] = true;
    }

    for (int i = 1; i <= 2 * N; ++i) {
        scanf("%d", &B[i]);
    }

    for (int i = 1; i <= N; ++i) {
        if (A[i] != B[i]) {
            printf("NO");
            free(A);
            free(B);
            free(exists);
            return 0;
        }
    }

    for (int i = N + 1; i <= 2 * N; ++i) {
        if (!exists[B[i]]) {
            printf("NO");
            free(A);
            free(B);
            free(exists);
            return 0;
        }
    }

    printf("YES");
    free(A);
    free(B);
    free(exists);
    return 0;
}