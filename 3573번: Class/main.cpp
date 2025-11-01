#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    long long n;
    int r, c;
    if (!(cin >> n >> r >> c)) return 0;

    int f = (int)min({(long long)((n + 1) / 2), (long long)r, (long long)c });

    vector<string> g(r, string(c, '.'));
    long long placed = 0;
    for (int j = 0; j < f; ++j) {
        g[0][j] = '#';
        ++placed;
    }

    for (int i = 0; i < f; ++i) {
        if (g[i][0] != '#') {
            g[i][0] = '#';
            ++placed;
        }
    }

    for (int i = 0; i < r && placed < n; ++i) {
        for (int j = 0; j < c && placed < n; ++j) {
            if (g[i][j] == '.') {
                g[i][j] = '#';
                ++placed;
            }
        }
    }

    cout << f << "\n";
    for (int i = 0; i < r; ++i) cout << g[i] << "\n";
    return 0;
}