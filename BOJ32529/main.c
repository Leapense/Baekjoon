#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int N;
    long long M;
    if (scanf("%d %lld", &N, &M) != 2) return 0;

    int *A = (int*)malloc((size_t)N * sizeof(int));
    if (!A) return 0;

    for (int i = 0; i < N; i++) scanf("%d", &A[i]);

    long long sum = 0;
    int ans = -1;

    for (int i = N - 1; i >= 0; i--) {
        sum += (long long) A[i];
        if (sum >= M) {
            ans = i + 1;
            break;
        }
    }

    printf("%d\n", ans);

    free(A);
    return 0;
}