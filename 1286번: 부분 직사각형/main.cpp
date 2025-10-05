#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    if (!(cin >> N >> M)) return 0;

    vector<string> g(N);
    for (int i = 0; i < N; ++i) cin >> g[i];

    using u64 = unsigned long long;
    using u128 = unsigned __int128;

    vector<u64> Hr(N), Hc(M);

    for (int r = 0; r < N; ++r) {
        u64 A = (u64)(r + 1) * (u64)(2 * N - r);
        u64 B = (u64)(r + N + 1) * (u64)(N - r);
        Hr[r] = A + B;
    }
    for (int c = 0; c < M; ++c) {
        u64 A = (u64)(c + 1) * (u64)(2 * M - c);
        u64 B = (u64)(c + M + 1) * (u64)(M - c);
        Hc[c] = A + B;
    }

    array<u64, 26> ans{};
    ans.fill(0);

    for (int r = 0; r < N; ++r) {
        for (int c = 0; c < M; ++c) {
            int k = g[r][c] - 'A';
            u128 prod = (u128)Hr[r] * (u128)Hc[c];
            ans[k] += (u64)prod;
        }
    }

    for (int i = 0; i < 26; ++i) {
        cout << ans[i] << '\n';
    }

    return 0;
}