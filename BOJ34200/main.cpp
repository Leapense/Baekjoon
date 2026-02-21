#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    long long prev;
    cin >> prev;

    long long ans = N;
    long long L0 = prev - 1;
    ans += (L0 + 1) / 2;

    for (int i = 2; i <= N; ++i) {
        long long x;
        cin >> x;

        long long diff = x - prev;
        if (diff == 1) {
            cout << -1 << '\n';
            return 0;
        }

        long long Li = diff - 2;
        ans += (Li + 1) / 2;
        prev = x;
    }

    cout << ans << '\n';
    return 0;
}