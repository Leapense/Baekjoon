#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        string s;
        cin >> s;

        string out;
        out.reserve(s.size() + 10);

        for (size_t i = 0; i < s.size(); ) {
            if (i + 1 < s.size() && s[i] == 'P' && s[i + 1] == 'O') {
                out += "PHO";
                i += 2;
            } else {
                out.push_back(s[i]);
                i += 1;
            }
        }

        cout << out << "\n";
    }

    return 0;
}
