#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int K;
    if (!(cin >> K)) return 0;

    cout.setf(ios::fixed);
    cout << setprecision(2);

    for (int tc = 1; tc <= K; ++tc) {
        int n, m;
        cin >> n >> m;

        vector<double> sx(n), sy(n);
        for (int  i = 0; i < n; ++i) {
            cin >> sx[i] >> sy[i];
        }

        vector<double> wx(m), wy(m), p(m);
        for (int j = 0; j < m; ++j) {
            cin >> wx[j] >> wy[j] >> p[j];
        }

        vector<vector<double>> dist(n, vector<double>(m));
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                double dx = sx[i] - wx[j];
                double dy = sy[i] - wy[j];
                dist[i][j] = hypot(dx, dy);
            }
        }

        int maxMask = 1 << m;
        const double INF = 1e100;

        vector<int> bitPos(maxMask, -1);
        for (int j = 0; j < m; ++j) {
            bitPos[1 << j] = j;
        }

        vector<double> buildCost(maxMask, 0.0);
        for (int mask = 1; mask < maxMask; ++mask) {
            int prev = mask & (mask - 1);
            int lsbBit = mask & -mask;
            int b = bitPos[lsbBit];
            buildCost[mask] = buildCost[prev] + p[b];
        }

        vector<double> shippingTotal(maxMask, 0.0);
        vector<double> g(maxMask, INF);

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

        cout << "Data Set " << tc << ":\n";
        cout << (ans + 1e-9) << "\n";
    }

    return 0;
}