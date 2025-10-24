#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    unsigned long long K;
    if (!(cin >> K)) return 0;

    unsigned long long x = K + 1ULL;
    unsigned msb = 63u - std::countl_zero(x);

    char buf[70];
    static constexpr char map01[2] = {'4', '7'};
    int n = 0;
    for (int b = (int)msb - 1; b >= 0; --b) {
        n += (buf[n] = map01[(x >> b) & 1ULL], 1);
    }

    cout.write(buf, n).put('\n');
    return 0;
}