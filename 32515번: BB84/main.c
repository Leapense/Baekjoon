#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    char *A = (char *)malloc((size_t)N + 5);
    char *K = (char *)malloc((size_t)N + 5);
    char *B = (char *)malloc((size_t)N + 5);
    char *M = (char *)malloc((size_t)N + 5);
    char *out = (char *)malloc((size_t)N + 5);

    if (!A || !K || !B || !M || !out) return 0;

    scanf("%s", A);
    scanf("%s", K);
    scanf("%s", B);
    scanf("%s", M);

    int out_len = 0;
    for (int i = 0; i < N; i++) {
        if (A[i] == B[i]) {
            if (K[i] != M[i]) {
                printf("htg!\n");
                free(A);
                free(K);
                free(B);
                free(M);
                free(out);
                return 0;
            }
            out[out_len++] = K[i];
        }
    }

    out[out_len] = '\0';
    printf("%s\n", out);

    free(A);
    free(K);
    free(B);
    free(M);
    free(out);
    return 0;
}