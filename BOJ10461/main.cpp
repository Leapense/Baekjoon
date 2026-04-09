#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int a, b;
    while (cin >> a >> b) {
        int d = abs(a - b);
        int r = (86400 - a) % d;

        long long M = (1440LL * r + d) / (2LL * d);
        M %= 720;

        int hour = static_cast<int>(M / 60);
        int minute = static_cast<int>(M % 60);

        if (hour == 0) hour = 12;
        cout << a << ' ' << b << ' '
             << setw(2) << setfill('0') << hour << ':'
             << setw(2) << setfill('0') << minute << '\n';
    }

    return 0;
}