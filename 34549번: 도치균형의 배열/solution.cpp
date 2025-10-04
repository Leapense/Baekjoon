#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int M;
    if (!(cin >> M)) return 0;
    bool first = true;
    for (int i = 1; i <= M; ++i) {
        if (!first) cout << ' ';
        cout << i;
        first = false;
    }
    for (int i = M; i >= 1; --i) {
        cout << ' ' << i;
    }
    cout << '\n';
    return 0;
}