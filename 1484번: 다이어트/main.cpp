#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long G;
    if (!(cin >> G)) return 0;
    
    vector<long long> ans;
    long long R = 1, C = 1;

    while (true) {
        long long diff = C * C - R * R;
        if (diff == G) {
            ans.push_back(C);
            ++R;
        } else if (diff > G) {
            ++R;
        } else {
            ++C;
        }

        if (2 * C - 1 > G) break;
    }

    if (ans.empty()) {
        cout << -1 << '\n';
    } else {
        for (auto w : ans) cout << w << '\n';
    }

    return 0;
}