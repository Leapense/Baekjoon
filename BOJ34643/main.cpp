#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<long long> a(n + 1), s(n);
    for (int i = 1; i <= n; ++i) cin >> a[i];
    for (int i = 0; i < n; ++i) cin >> s[i];

    sort(s.begin(), s.end(), greater<long long>());

    vector<long long> pref(n + 1, 0);
    for (int i = 1; i <= n; ++i) {
        pref[i] = pref[i - 1] + s[i - 1];
    }

    long double ans = 0.0L;
    for (int k = 1; k <= n; ++k) {
        long double cur = (static_cast<long double>(pref[k]) + a[k]) / k;
        ans = max(ans, cur);
    }

    cout << fixed << setprecision(10) << static_cast<double>(ans) << '\n';
    return 0;
}