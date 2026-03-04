#include <bits/stdc++.h>
using namespace std;

static string phase_of_day(int d) {
    if (d == 0) return "New";
    if (1 <= d && d <= 4) return "Crescent";
    if (5 <= d && d <= 8) return "Quarter";
    if (9 <= d && d <= 13) return "Gibbous";
    if (d == 14) return "Full";
    if (15 <= d && d <= 19) return "Gibbous";
    if (20 <= d && d <= 22) return "Quarter";
    return "Crescent";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<string> obs(n);
    for (auto &s : obs) cin >> s;

    for (int k = 1; k <= 28; k++) {
        bool ok = true;
        for (int i = 1; i <= n; i++) {
            int d = (i * k) % 28;
            if (phase_of_day(d) != obs[i - 1]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            cout << k;
            return 0;
        }
    }

    return 0;
}