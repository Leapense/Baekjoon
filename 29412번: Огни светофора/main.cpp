#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long g, gb, y, r, ry;
    long long T;
    cin >> g >> gb >> y >> r >> ry;
    cin >> T;

    long long red2 = 0, yellow2 = 0, green2 = 0;
    long long rem = T;

    while (rem > 0) {
        long long x;

        x = min(rem, g);
        green2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = min(rem, gb);
        green2 += x;
        rem -= x;
        if (rem == 0) break;

        x = min(rem, y);
        yellow2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = min(rem, r);
        red2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = min(rem, ry);
        red2 += 2 * x;
        yellow2 += 2 * x;
        rem -= x;
    }

    cout << (red2 / 2) << ' ' << (yellow2 / 2) << ' ' << (green2 / 2) << '\n';
    return 0;
}