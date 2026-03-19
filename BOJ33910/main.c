#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;
    
    int *A = (int *)malloc(sizeof(int) * (size_t)N);
    if (A == NULL) return 0;

    for (int i = 0; i < N; ++i) {
        scanf("%d", &A[i]);
    }

    long long answer = 0;
    int mn = 1000000001;

    for (int i = N - 1; i >= 0; --i) {
        if (A[i] < mn) mn = A[i];
        answer += (long long)mn;
    }

    printf("%lld", answer);

    free(A);
    return 0;
}