#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, k, T0;
    cin >> n >> k >> T0;

    long long ans = 0;
    for (int i = 0; i < n; i++) {
        int d;
        cin >> d;
        ans += (long long) d;
    }

    cout << ans << "\n";
    return 0;
}