#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long N;
    cin >> N;

    long long ans = N * (N - 1) * (N - 2) * (N - 3) * (N - 4) / 120;
    cout << ans << '\n';

    return 0;
}