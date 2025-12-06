#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!(cin >> n)) return 0;

    vector<double> P(n), R(n);
    for (int i = 0; i < n; ++i) {
        cin >> P[i] >> R[i];
    }

    const long double INF = 1e50L;
    vector<vector<long double>> cost(n, vector<long double>(n));
    for (int i = 0; i < n; ++i) {
        for (int t = 0; t < n; ++t) {
            cost[i][t] = P[i] * powl(R[i], (long double)t);
        }
    }
    vector<long double> u(n + 1), v(n + 1);
    vector<int> p(n + 1), way(n + 1);

    for (int i = 1; i <= n; ++i) {
        p[0] = i;
        int j0 = 0;
        vector<long double> minv(n + 1, INF);
        vector<bool> used(n + 1, false);

        while (true) {
            used[j0] = true;
            int i0 = p[j0];
            int j1 = 0;
            long double delta = INF;

            for (int j = 1; j <= n; ++j) if (!used[j]) {
                long double cur = cost[i0 - 1][j - 1] - u[i0] - v[j];
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
                    v[j] -= delta;
                } else {
                    minv[j] -= delta;
                }
            }

            j0 = j1;
            if (p[j0] == 0) break;
        }

        while (true) {
            int j1 = way[j0];
            p[j0] = p[j1];
            j0 = j1;
            if (j0 == 0) break;
        }
    }

    long double ans = -v[0];
    cout.setf(ios::fixed);
    cout << setprecision(2) << (double)ans << '\n';
    return 0;
}