#include <stdio.h>
#include <limits.h>

#define MAXN 100
#define MAXM 10000

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) return 0;

    int A[MAXM + 1];
    for (int i = 1; i <= M; i++) scanf("%d", &A[i]);

    long long dist[MAXN + 1][MAXN + 1];

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            long long x;
            scanf("%lld", &x);
            dist[i][j] = x;
        }
    }

    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                long long via = dist[i][k] + dist[k][j];
                if (via < dist[i][j]) dist[i][j] = via;
            }
        }
    }

    long long ans = 0;
    for (int i = 1; i < M; i++) {
        ans += dist[A[i]][A[i + 1]];
    }

    printf("%lld\n", ans);
    return 0;
}