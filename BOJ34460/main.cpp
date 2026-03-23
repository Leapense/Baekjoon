#include <bits/stdc++.h>
using namespace std;

static bool is_valid(const string& s) {
    int n = (int)s.size();
    int i = 0;
    while (i < n) {
        if (s[i] != 'M') return false;
        ++i;

        if (i + 1 >= n || s[i] != 'I' || s[i + 1] != 'T') return false;
        i += 2;

        while (i < n && s[i] == 'I') {
            if (i + 1 >= n || s[i + 1] != 'T') return false;
            i += 2;
        }

        if (i < n && s[i] != 'M') return false;
    }

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int len;
        string s;
        cin >> len >> s;

        cout << (is_valid(s) ? "YES" : "NO") << '\n';
    }

    return 0;
}