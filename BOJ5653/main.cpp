#include <bits/stdc++.h>
using namespace std;

static string to_binary_u32(uint32_t x) {
    int msb = __bit_width(x) - 1;
    string s;
    s.reserve(msb + 1);
    for (int i = msb; i >= 0; --i) {
        s.push_back(((x >> i) & 1u) ? '1' : '0');
    }

    return s;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    uint32_t n;
    while (cin >> n && n != 0u) {
        vector<string> v;
        uint32_t x = n;
        while (x > 1u) {
            v.push_back(to_binary_u32(x));
            x = static_cast<uint32_t>(__bit_width(x) - 1);
        }

        cout << '0';
        for (int i = (int)v.size() - 1; i >= 0; --i) cout << v[i];
        cout << '\n';
    }

    return 0;
}