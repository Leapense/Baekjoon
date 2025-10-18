#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    vector<long long> a(N);
    long long S = 0;
    for (int i = 0; i < N; ++i) {
        cin >> a[i];
        S += a[i];
    }

    vector<long long> b(2 * N);
    for (int i = 0; i < 2 * N; ++i) b[i] = a[i % N];
    long long half = S / 2;
    long long ans = 0, cur = 0;
    int j = 0;
    for (int i = 0; i < N; ++i) {
        while (j < i + N && cur + b[j] <= half) {
            cur += b[j];
            ++j;
        }
        ans = max(ans, cur);
        cur -= b[i];
    }

    cout << ans << '\n';
    return 0;
}