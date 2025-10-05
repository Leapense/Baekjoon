#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    
    vector<int> a(N);
    int maxV = 0;
    for (int i = 0; i < N; ++i) {
        cin >> a[i];
        if (a[i] > maxV) maxV = a[i];
    }

    vector<int> freq(maxV + 1, 0), divCount(maxV + 1, 0);
    for (int v : a) ++freq[v];
    for (int d = 1; d <= maxV; ++d) {
        int fd = freq[d];
        if (fd == 0) continue;
        for (int m = d; m <= maxV; m += d) {
            divCount[m] += fd;
        }
    }

    for (int i = 0; i < N; ++i) {
        int ans = divCount[a[i]] - 1;
        cout << ans << '\n';
    }

    return 0;
}