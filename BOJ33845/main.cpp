#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string S, T;
    cin >> S >> T;

    array<bool, 26> present{};
    for (char c : S) present[c - 'a'] = true;

    string out;
    out.reserve(T.size());

    for (char c : T) {
        if (!present[c - 'a']) out.push_back(c);
    }

    cout << out << "\n";
    return 0;
}
