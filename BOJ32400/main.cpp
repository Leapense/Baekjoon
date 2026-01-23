#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    array<int, 20> a{};
    for (int i = 0; i < 20; i++) cin >> a[i];

    int pos = -1;
    for (int i = 0; i < 20; i++) {
        if (a[i] == 20) { pos = i; break; }
    }

    int left = a[(pos + 19) % 20];
    int right = a[(pos + 1) % 20];
    int aliceSum = left + 20 + right;

    int lhs = 2 * aliceSum;
    int rhs = 63;

    if (lhs > rhs) cout << "Alice\n";
    else if (lhs < rhs) cout << "Bob\n";
    else cout << "Tie\n";

    return 0;
}