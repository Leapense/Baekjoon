#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long xA, yA, xB, yB;
    long long a, b;
    cin >> xA >> yA;
    cin >> xB >> yB;
    cin >> a >> b;

    long long dx = llabs(xB - xA);
    long long dy = llabs(yB - yA);

    long long low = (dy + b - 1) / b;
    long long high = dx / a;
    if (high <= 0 || low > high) {
        cout << -1 << "\n";
        return 0;
    }

    long long n = high;
    cout << dx << ' ' << n << "\n";
    cout << dy << ' ' << n << "\n";
    return 0;
}