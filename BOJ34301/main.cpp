#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    array<int, 5> denom = {1, 5, 15, 30, 150};
    array<int, 5> cnt = {0, 0, 0, 0, 0};

    for (int i = 4; i >= 0; --i) {
        cnt[i] = N / denom[i];
        N %= denom[i];
    }

    cout << cnt[0] << ' ' << cnt[1] << ' ' << cnt[2] << ' ' << cnt[3] << ' ' << cnt[4] << "\n";
    return 0;
}