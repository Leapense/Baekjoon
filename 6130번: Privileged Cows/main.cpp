#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> a(N);
    int c1 = 0, c2 = 0, c3 = 0;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        if (a[i] == 1) c1++;
        else if (a[i] == 2) c2++;
        else c3++;
    }

    array<array<int, 4>, 3> seg{};
    int end1 = c1;
    int end2 = c1 + c2;

    for (int i = 0; i < N; i++) {
        int region = (i < end1) ? 0 : (i < end2) ? 1 : 2;
        seg[region][a[i]]++;
    }

    auto take = [&](int regionA, int valA, int regionB, int valB) {
        int x = min(seg[regionA][valA], seg[regionB][valB]);
        seg[regionA][valA] -= x;
        seg[regionB][valB] -= x;
        return x;
    };

    int ans = 0;
    ans += take(0, 2, 1, 1);
    ans += take(0, 3, 2, 1);
    ans += take(1, 3, 2, 2);

    int remaining = seg[0][2] + seg[0][3] + seg[1][1] + seg[1][3] + seg[2][1] + seg[2][2];

    ans += 2 * (remaining / 3);
    cout << ans << "\n";

    return 0;
}