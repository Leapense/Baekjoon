#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long N, M, S;
    cin >> N >> M >> S;

    long long percent_cost = ((M + 1) * S * (100 - N)) / 100;
    long long promo_cost = M * S;

    cout << min(percent_cost, promo_cost) << "\n";
    return 0;
}