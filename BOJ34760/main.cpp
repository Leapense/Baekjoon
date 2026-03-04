#include <iostream>
#include <array>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    array<long long, 15> a{};
    for (int i = 0; i < 15; i++) {
        cin >> a[i];
    }

    long long max_first14 = *max_element(a.begin(), a.begin() + 14);
    long long ans = (a[14] > max_first14) ? a[14] : (max_first14 + 1);

    cout << ans;

    return 0;
}