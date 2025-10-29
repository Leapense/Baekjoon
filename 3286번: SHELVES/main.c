#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int C, R;
    if (scanf("%d %d", &C, &R) != 2) return 0;
    int N;
    scanf("%d", &N);

    int *H = calloc(C + 2, sizeof(int));
    for (int i = 0; i < N; ++i) {
        int A, B;
        scanf("%d %d", &A, &B);
        if (A >= 1 && A <= C && B >= 1 && B <= R) {
            if (B > H[A]) H[A] = B;
        }
    }

    if (C == 1) {
        printf("%d\n", H[1]);
        free(H);
        return 0;
    }

    const int INF = 1000000000;
    int S = R + 1;

    int **dp = malloc(S * sizeof(*dp));
    int **nxt = malloc(S * sizeof(*nxt));

    for (int i = 0; i < S; ++i) {
        dp[i] = malloc(S * sizeof(int));
        nxt[i] = malloc(S * sizeof(int));
        for (int j = 0; j < S; ++j) dp[i][j] = INF, nxt[i][j] = INF;
    }

    for (int b = 0; b <= R; ++b) dp[0][b] = b;
    for (int i = 1; i <= C - 1; ++i) {
        for (int x = 0; x <= R; ++x) {
            for (int y = 0; y <= R; ++y) {
                nxt[x][y] = INF;
            }
        }

        int need = H[i];
        for (int a = 0; a <= R; ++a) {
            for (int b = 0; b <= R; ++b) {
                int cur = dp[a][b];
                if (cur >= INF) continue;
                int m = (a > b) ? a : b;
                int cmin = (m >= need) ? 0 : need;
                for (int c = cmin; c <= R; ++c) {
                    int val = cur + c;
                    if (val < nxt[b][c]) nxt[b][c] = val;
                }
            }
        }
        int **tmp = dp;
        dp = nxt;
        nxt = tmp;
    }

    int ans = INF, need = H[C];
    for (int a = 0; a <= R; ++a) {
        for (int b = 0; b <= R; ++b) {
            if ((a > b ? a : b) >= need && dp[a][b] < ans)
                ans = dp[a][b];
        }
    }

    printf("%d\n", ans);
    for (int i = 0; i < S; ++i) { free(dp[i]); free(nxt[i]); }
    free(dp);
    free(nxt);
    free(H);

    return 0;
}