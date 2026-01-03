#include <bits/stdc++.h>
using namespace std;

static long long ceil_div(long long x, long long y) {
    return (x + y - 1) / y;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long a, b, h, w;
    cin >> a >> b >> h >> w;

    long long k = ceil_div(h, a);
    long long l = ceil_div(w, b);

    cout << k * l << "\n";
    return 0;
}