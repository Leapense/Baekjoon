#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long double d, h;
    cin >> d >> h;

    const long double pi = acosl(-1.0L);
    long double r = d / 2.0L + 5.0L;


    long double area = pi * r * (2.0L * h + r);

    cout << fixed << setprecision(17) << area << "\n";
    return 0;
}
