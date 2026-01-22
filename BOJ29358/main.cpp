#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int n;
    cin >> n;

    array<int, 3> a{0, 0, 0}, b{0, 0, 0}, c{0, 0, 0};
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < n; i++) cin >> b[i];
    for (int i = 0; i < n; i++) cin >> c[i];
    
    int ans = 0;
    for (int i = 0; i < n; i++) {
        int L = min(b[i], c[i]);
        int R = max(b[i], c[i]);
        if (L > 0) ans++;
        if (R < a[i]) ans++;
    }

    cout << ans << "\n";
    return 0;
}