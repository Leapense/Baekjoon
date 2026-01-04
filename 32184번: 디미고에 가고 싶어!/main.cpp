#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int A, B;
    cin >> A >> B;

    int total = B - A + 1;
    int k_low = (A + 2) / 2;
    int k_high = B / 2;

    int pairs = (k_low <= k_high) ? (k_high - k_low + 1) : 0;
    cout << total - pairs << "\n";
    return 0;
}