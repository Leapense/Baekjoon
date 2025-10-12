#include <bits/stdc++.h>
using namespace std;

static inline int idx(char c) {
    return (c >= 'A' && c <= 'Z') ? (c - 'A') : (c - 'a' + 26);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int g, n;
    if (!(cin >> g >> n)) return 0;
    string W, S;
    W.reserve(g);
    S.reserve(n);
    cin >> W >> S;

    g = (int)W.size();
    n = (int)S.size();

    array<int, 52> bal{};
    for (char c : W) bal[idx(c)]--;
    for (int i = 0; i < g; ++i) bal[idx(S[i])]++;

    int diff = 0;
    for (int v : bal) if (v != 0) ++diff;

    long long ans = (diff == 0) ? 1 : 0;

    for (int i = g; i < n; ++i) {
        int oi = idx(S[i - g]);

        if (bal[oi] == 0) ++diff;
        else if (bal[oi] == 1) --diff;
        --bal[oi];

        int ni = idx(S[i]);
        if (bal[ni] == 0) ++diff;
        else if (bal[ni] == -1) --diff;
        ++bal[ni];

        if (diff == 0) ++ans;
    }

    cout << ans << '\n';

    return 0;
}