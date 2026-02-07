#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int a, b, c, d, e;
    cin >> a >> b >> c >> d >> e;

    int U = min(b, c - 1);
    int hi = c - d;
    int lo = c - e;

    int S_above = U - hi;
    int S_below = lo - a;
    int S_mid_max = max(0, e - d - 1);
    int S_high_max = max(0, b - c + 1);

    int min_off = S_above + S_below;
    int max_off = S_mid_max + S_high_max;

    cout << min_off << " " << max_off << "\n";

    return 0;
}