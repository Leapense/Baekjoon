#include <stdio.h>
#include <math.h>
#include <stdbool.h>

#define MAXN 100
#define INF 1e50L

long double cost[MAXN][MAXN];
long double u[MAXN + 1], v_[MAXN + 1];
int p[MAXN + 1], way[MAXN + 1];
long double minv[MAXN + 1];
bool used[MAXN + 1];

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    long double P[MAXN], R[MAXN];
    for (int i = 0; i < n; ++i) {
        double p_in, r_in;
        if (scanf("%lf %lf", &p_in, &r_in) != 2) return 0;
        P[i] = (long double)p_in;
        R[i] = (long double)r_in;
    }

    for (int i = 0; i < n; ++i) {
        for (int t = 0; t < n; ++t) {
            cost[i][t] = P[i] * powl(R[i], (long double)t);
        }
    }

    for (int i = 0; i <= n; ++i) {
        u[i] = 0.0L;
        v_[i] = 0.0L;
        p[i] = 0;
        way[i] = 0;
    }
    for (int i = 1; i <= n; ++i) {
        p[0] = i;
        int j0 = 0;
        for (int j = 0; j <= n; ++j) {
            minv[j] = INF;
            used[j] = false;
        }

        while (1) {
            used[j0] = true;
            int i0 = p[j0];
            int j1 = 0;
            long double delta = INF;

            for (int j = 1; j <= n; ++j) if (!used[j]) {
                long double cur = cost[i0 - 1][j - 1] - u[i0] - v_[j];
                if (cur < minv[j]) {
                    minv[j] = cur;
                    way[j] = j0;
                }
                if (minv[j] < delta) {
                    delta = minv[j];
                    j1 = j;
                }
            }
            for (int j = 0; j <= n; ++j) {
                if (used[j]) {
                    u[p[j]] += delta;
                    v_[j] -= delta;
                } else {
                    minv[j] -= delta;
                }
            }
            j0 = j1;
            if (p[j0] == 0) break;
        }
        while (1) {
            int j1 = way[j0];
            p[j0] = p[j1];
            j0 = j1;
            if (j0 == 0) break;
        }
    }

    long double ans = -v_[0];
    printf("%.2Lf\n", ans);
    return 0;
}