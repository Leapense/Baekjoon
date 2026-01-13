#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        long long N;
        cin >> N;

        long long r = (long long) sqrtl((long double)N);

        while (r * r < N) r++;
        while (r * r > N) r--;

        cout << ((r * r == N) ? 1 : 0) << '\n';
    }

    return 0;
}
