#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    while (true) {
        int k;
        if (!(cin >> k)) return 0;
        if (k == 0) break;

        vector<pair<long double, long double>> p(k);
        for (int i = 0; i < k; ++i) {
            long double x, y;
            cin >> x >> y;
            p[i] = {x, y};
        }

        long double S = 0.0L;
        for (int i = 0; i < k; ++i) {
            int j = (i + 1) % k;
            S += p[i].first * p[j].second - p[j].first * p[i].second;
        }

        long double area = fabsl(S) * 0.5L;
        long double ans = llround(area);

        cout << ans << '\n';
    }

    return 0;
}