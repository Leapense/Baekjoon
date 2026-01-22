#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    uint32_t N;
    cin >> N;

    vector<uint8_t> bytes;
    uint32_t temp = N;

    while (temp > 0) {
        bytes.push_back(static_cast<uint8_t>(temp & 255u));
        temp >>= 8;
    }

    uint32_t M = 0;
    for (uint8_t b : bytes) {
        M = (M << 8) | b;
    }

    cout << M << "\n";
    return 0;
}