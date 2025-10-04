#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    const int MAXM = 1'000'000;
    vector<int> need(MAXM + 1, 0);
    long long min_groups = 0;

    for (int i = 0; i < N; ++i) {
        string tok;
        cin >> tok;
        int v = 0;
        if (!tok.empty() && isdigit(static_cast<unsigned char>(tok[0]))) {
            int m = 0;
            for (char c : tok) m = m * 10 + (c - '0');
            string back;
            cin >> back;
            v = m;
        } else {
            string me;
            cin >> me;
            v = 0;
        }

        if (need[v] > 0) {
            --need[v];
        } else {
            ++min_groups;
        }

        if (v > 0) ++need[v - 1];
    }

    long long max_groups = N;
    cout << min_groups << ' ' << max_groups << '\n';
    return 0;
}