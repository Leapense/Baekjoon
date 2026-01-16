#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, p;
    cin >> n >> p;

    int total = 0;
    for (int k = 0; k < n; k++) total += (p + k);

    int given = 0;
    for (int i = 0; i < n - 1; i++) {
        int x;
        cin >> x;
        given += x;
    }

    cout << (total - given) << '\n';

    return 0;
}