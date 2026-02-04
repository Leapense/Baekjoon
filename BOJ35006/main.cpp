#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    double n, p;
    cin >> n >> p;
    double loss = n * p * 55.0 / 1000.0;
    cout << fixed << setprecision(10) << loss << "\n";
    return 0;
}