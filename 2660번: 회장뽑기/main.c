#include <stdio.h>

#define MAXN 51
#define INF 1000000000

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    static int dist[MAXN][MAXN];
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= N; ++j) {
            dist[i][j] = (i == j) ? 0 : INF;
        }
    }
    
    while (1) {
        int a, b;
        if (scanf("%d %d", &a, &b) != 2) break;
        if (a == -1 && b == -1) break;
        dist[a][b] = 1;
        dist[b][a] = 1;
    }

    for (int k = 1; k <= N; ++k) {
        for (int i = 1; i <= N; ++i) {
            if (dist[i][k] == INF) continue;
            for (int j = 1; j <= N; ++j) {
                int via = dist[i][k] + dist[k][j];
                if (via < dist[i][j]) dist[i][j] = via;
            }
        }
    }

    static int score[MAXN];
    int best = INF;
    for (int i = 1; i <= N; ++i) {
        int mx = 0;
        for (int j = 1; j <= N; ++j) {
            if (dist[i][j] > mx) mx = dist[i][j];
        }
        score[i] = mx;
        if (mx < best) best = mx;
    }

    int cnt = 0;
    for (int i = 1; i <= N; ++i) if (score[i] == best) ++cnt;

    printf("%d %d\n", best, cnt);
    for (int i = 1; i <= N; ++i) {
        if (score[i] == best) {
            printf("%d", i);
            if (--cnt) printf(" ");
        }
    }
    printf("\n");
    return 0;
}