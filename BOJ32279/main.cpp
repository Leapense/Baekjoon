#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long p, q, r, s;
    long long a1;

    cin >> n;
    cin >> p >> q >> r >> s;
    cin >> a1;

    array<long long, 21> a{};
    a[1] = a1;

    long long sum = a[1];
    for (int i = 2; i <= n; i++) {
        int parent = i / 2;
        if (i % 2 == 0) a[i] = p * a[parent] + q;
        else            a[i] = r * a[parent] + s;
        sum += a[i];
    }

    cout << sum << "\n";
    return 0;
}