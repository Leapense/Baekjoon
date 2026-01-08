#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    long long cur = 0, ans = 0;
    for (int i = 0; i < n; i++) {
        long long a, b;
        cin >> a >> b;
        cur -= a;
        cur += b;
        ans = max(ans, cur);
    }

    cout << ans << '\n';
    return 0;
}
