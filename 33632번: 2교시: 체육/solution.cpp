#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    long double W, H;
    long double x, y;
    long double x1, y1, x2, y2;
    if (!(cin >> W >> H)) return 0;
    cin >> x >> y;
    cin >> x1 >> y1 >> x2 >> y2;
    if (x1 > x2) swap(x1, x2);

    long double ans = 0.0L;

    if (y < y1) {
        ans = 0.0L;
    } else if (y == 0.0L) {
        if (y1 != 0.0L) {
            ans = 0.0L;
        } else {
            if (x >= x1 && x <= x2) {
                ans = 1.0L;
            } else if (x < x1) {
                long double len = max(0.0L, W - x1);
                ans = (W == 0.0L ? 0.0L : len / W);
            } else {
                long double len = max(0.0L, min(W, x2));
                ans = (W == 0.0L ? 0.0L : len / W);
            }
        }
    } else {
        long double alpha = 1.0L - y1 / y;
        long double beta = x * (y1 / y);
        long double L = (x1 - beta) / alpha;
        long double R = (x2 - beta) / alpha;
        if (L > R) swap(L, R);

        long double left = max(0.0L, L);
        long double right = min(W, R);
        long double len = max(0.0L, right - left);
        ans = (W == 0.0L ? 0.0L : len / W);
    }

    cout.setf(std::ios::fixed);
    cout << setprecision(12) << (double)ans << "\n";
    return 0;
}