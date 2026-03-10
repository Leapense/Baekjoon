#include <iostream>
#include <algorithm>
#include <string>
using namespace std;

using ull = unsigned long long;
using u128 = unsigned __int128;

static void print_u128(u128 x) {
    if (x == 0) {
        cout << 0;
        return;
    }

    string s;
    while (x > 0) {
        s.push_back(char('0' + x % 10));
        x /= 10;
    }

    reverse(s.begin(), s.end());
    cout << s;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    ull a, b, c;
    cin >> a >> b >> c;

    u128 answer = (u128)a + (u128)b * (u128)(a - a / c);
    print_u128(answer);
    return 0;
}