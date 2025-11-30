#include <stdio.h>
#include <math.h>

#define MAX_N 100
#define MAX_M 20

int main(void) {
    int K;
    if (scanf("%d", &K) != 1) return 0;
    for (int tc = 1; tc <= K; ++tc) {
        int n, m;
        if (scanf("%d %d", &n, &m) != 2) return 0;

        double sx[MAX_N], sy[MAX_N];
        for (int i = 0; i < n; ++i) {
            scanf("%lf %lf", &sx[i], &sy[i]);
        }

        double wx[MAX_M], wy[MAX_M], p[MAX_M];
        for (int j = 0; j < m; ++j) {
            scanf("%lf %lf %lf", &wx[j], &wy[j], &p[j]);
        }

        double dist[MAX_N][MAX_M];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                double dx = sx[i] - wx[j];
                double dy = sy[i] - wy[j];
                dist[i][j] = hypot(dx, dy);
            }
        }

        int maxMask = 1 << m;
        const double INF = 1e100;

        static int bitPos[1 << MAX_M];
        for (int i = 0; i < maxMask; ++i) bitPos[i] = -1;
        for (int j = 0; j < m; ++j) {
            bitPos[1 << j] = j;
        }

        static double buildCost[1 << MAX_M];
        buildCost[0] = 0.0;
        for (int mask = 1; mask < maxMask; ++mask) {
            int prev = mask & (mask - 1);
            int lsbBit = mask & -mask;
            int b = bitPos[lsbBit];
            buildCost[mask] = buildCost[prev] + p[b];
        }

        static double shippingTotal[1 << MAX_M];
        static double g[1 << MAX_M];

        for (int mask = 0; mask < maxMask; ++mask) {
            shippingTotal[mask] = 0.0;
        }

        for (int i = 0; i < n; ++i) {
            g[0] = INF;
            for (int mask = 1; mask < maxMask; ++mask) {
                int prev = mask & (mask - 1);
                int lsbBit = mask & -mask;
                int b = bitPos[lsbBit];
                double prevMin = g[prev];
                double cand = dist[i][b];
                g[mask] = (cand < prevMin) ? cand : prevMin;
                shippingTotal[mask] += g[mask];
            }
        }

        double ans = INF;
        for (int mask = 1; mask < maxMask; ++mask) {
            double totalCost = buildCost[mask] + shippingTotal[mask];
            if (totalCost < ans) ans = totalCost;
        }

        printf("Data Set %d:\n", tc);
        printf("%.2f\n", ans + 1e-9);
    }

    return 0;
}