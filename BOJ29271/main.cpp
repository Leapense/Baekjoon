#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long r, x;
    cin >> n >> r >> x;

    long long cur = 0, ans = 0;
    for (int i = 0; i < n; i++) {
        long long a;
        cin >> a;

        cur += a;
        if (cur > r) cur = r;
        if (cur <= x) {
            ans += cur;
            cur = 0;
        } else {
            ans += x;
            cur -= x;
        }
    }

    cout << ans << "\n";
    return 0;
}