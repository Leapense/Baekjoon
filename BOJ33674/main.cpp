#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, D, K;
    cin >> N >> D >> K;

    int L = INT_MAX;
    for (int i = 0; i < N; ++i) {
        int s; cin >> s;
        L = min(L, K / s);
    }

    int ans = (D - 1) / L;
    cout << ans << '\n';
    return 0;
}