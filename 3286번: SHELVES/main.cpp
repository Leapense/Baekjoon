#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int C, R;
    if (!(cin >> C >> R)) return 0;
    int N;
    cin >> N;
    vector<int> H(C + 2, 0);
    for (int i = 0; i < N; ++i) {
        int A, B; cin >> A >> B;
        H[A] = max(H[A], B);
    }

    if (C == 1) { cout << H[1] << '\n'; return 0; }

    const int INF = 1e9;
    const int S = R + 1;

    vector<vector<int>> dp(S, vector<int>(S, INF)), nxt(S, vector<int>(S, INF));
    for (int b = 0; b <= R; ++b) dp[0][b] = b;
    for (int i = 1; i <= C - 1; ++i) {
        for (int x = 0; x <= R; ++x) fill(nxt[x].begin(), nxt[x].end(), INF);
        int need = H[i];
        for (int a = 0; a <= R; ++a) {
            for (int b = 0; b <= R; ++b) {
                int cur = dp[a][b];
                if (cur >= INF) continue;
                int m = max(a, b);
                int cmin = (m >= need) ? 0 : need;
                for (int c = cmin; c <= R; ++c) {
                    int &ref = nxt[b][c];
                    int val = cur + c;
                    if (val < ref) ref = val;
                }
            }
        }
        dp.swap(nxt);
    }

    int ans = INF, need = H[C];
    for (int a = 0; a <= R; ++a) {
        for (int b = 0; b <= R; ++b) {
            if (max(a, b) >= need) ans = min(ans, dp[a][b]);
        }
    }

    cout << ans << '\n';
    return 0;
}