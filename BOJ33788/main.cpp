#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    while (t--) {
        int n;
        string s;
        cin >> n >> s;

        cout << ((s.front() == 'R' || s.back() == 'L') ? "YES" : "NO") << "\n";
    }
    return 0;
}