#include <bits/stdc++.h>
using namespace std;

static int move_id(const string& s) {
    if (!s.empty() && s[0] == 'r') return 0;
    if (!s.empty() && s[0] == 'p') return 1;
    return 2;
}

static int result(int a, int b) {
    if (a == b) return 0;
    int d = (a - b + 3) % 3;
    return (d == 1) ? 1 : -1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, k;
    bool first_case = true;

    while (cin >> n) {
        if (n == 0) break;
        cin >> k;

        int total_games = k * n * (n - 1) / 2;
        vector<int> w(n + 1, 0), l(n + 1, 0);

        for (int i = 0; i < total_games; i++) {
            int p1, p2;
            string m1s, m2s;
            cin >> p1 >> m1s >> p2 >> m2s;

            int m1 = move_id(m1s);
            int m2 = move_id(m2s);
            int r = result(m1, m2);

            if (r == 1) { w[p1]++; l[p2]++; }
            else if (r == -1) { w[p2]++; l[p1]++;}
        }

        if (!first_case) cout << "\n";
        first_case = false;

        cout.setf(std::ios::fixed);
        cout << setprecision(3);

        for (int p = 1; p <= n; p++) {
            int denom = w[p] + l[p];
            if (denom == 0) cout << "-\n";
            else cout << (double)w[p] / (double)denom << '\n';
        }
    }

    return 0;
}