#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    int cur = 0, ans = 0;
    for (int i = 0; i < N; ++i) {
        int S, T;
        cin >> S >> T;

        if (S > T) ++cur;
        else cur = 0;

        ans = max(ans, cur);
    }

    cout << ans;

    return 0;
}