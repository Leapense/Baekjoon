#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long cur;
    if (!(cin >> cur)) return 0;

    long long x;
    while (cin >> x) {
        if (x < cur) cur += x;
        else {
            cout << cur << "\n";
            return 0;
        }
    }

    cout << cur << "\n";
    return 0;
}