#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    while (n--) {
        int k;
        string s;
        cin >> k >> s;

        int m = (int)s.size();
        int L = m - k;

        if (L == 1) {
            bool hasZero = false;
            for (char c : s) {
                if (c == '0') {
                    hasZero = true;
                    break;
                }
            }

            cout << (hasZero ? "0" : "-1") << '\n';
            continue;
        }

        if (L == 2) {
            cout << -1 << '\n';
            continue;
        }

        int zeroCount = 0;
        for (int i = L - 2; i < m; ++i) {
            if (s[i] == '0') {
                ++zeroCount;
            }
        }

        if (zeroCount >= 2) {
            cout << s.substr(0, L - 2) << "00\n";
        } else {
            cout << -1 << '\n';
        }
    }

    return 0;
}