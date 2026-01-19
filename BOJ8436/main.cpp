#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string s;
    cin >> s;

    long long ans = 1;
    for (char c : s) {
        if (c == 'T' || c == 'D' || c == 'L' || c == 'F') {
            ans *= 2;
        }
    }
    cout << ans << "\n";
    return 0;
}