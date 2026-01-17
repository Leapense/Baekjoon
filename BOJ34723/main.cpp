#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int P, M, C;
    long long X;
    cin >> P >> M >> C;
    cin >> X;

    long long ans = (long long)9e18;

    for (int p = 1; p <= P; ++p) {
        for (int m = 1; m <= M; ++m) {
            for (int c = 1; c <= C; ++c) {
                long long v = 1LL * (p + m) * (m + c);
                long long diff = llabs(v - X);
                ans = min(ans, diff);
            }
        }
    }
    cout << ans << "\n";
    return 0;
}
