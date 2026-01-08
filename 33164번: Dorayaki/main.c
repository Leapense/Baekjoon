#include <stdio.h>

static inline int imax(int a, int b) { return a > b ? a : b; }

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) return 0;

    int A[105], B[105];

    for (int i = 0; i < N; i++) scanf("%d", &A[i]);
    for (int j = 0; j < M; j++) scanf("%d", &B[j]);

    long long ans = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            int mx = imax(A[i], B[j]);
            ans += (long long)(A[i] + B[j]) * (long long)mx;
        }
    }

    printf("%lld\n", ans);

    return 0;
}
