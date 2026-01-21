#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long X, Y;
    cin >> X >> Y;
    (void)Y;

    double ans = (double)X * (1.0 - 1.0 / sqrt(2.0));

    cout << fixed << setprecision(10) << ans << "\n";
    return 0;
}