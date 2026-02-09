#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    long long L = (long long)4e18;
    long long R = (long long)-4e18;

    for (int i = 1; i <= n; i++) {
        int a;
        cin >> a;
        long long d = (long long)a - (long long)i;
        L = min(L, d);
        R = max(R, d);
    }

    long long ans = (R - L) + min(llabs(L), llabs(R));
    cout << ans << '\n';

    return 0;
}