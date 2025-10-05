#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long N;
    if (!(cin >> N)) return 0;

    if (N > 1023) {
        cout << -1 << '\n';
        return 0;
    }

    vector<unsigned long long> v;
    v.reserve(1023);
    for (int mask = 1; mask < (1 << 10); ++mask) {
        unsigned long long num = 0;
        for (int d = 9; d >= 0; --d) {
            if (mask & (1 << d)) num = num * 10 + (unsigned)d;
        }
        v.push_back(num);
    }
    sort(v.begin(), v.end());
    cout << v[N - 1] << '\n';
    return 0;
}