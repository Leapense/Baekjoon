#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, k;
    cin >> n >> k;

    int open = 0;
    for (int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;

        if (open <= a + b) open = b;
        else open -= a;
    }

    cout << (k - open) << '\n';
    return 0;
}