#include <bits/stdc++.h>
using namespace std;

static inline uint32_t bitlen(uint32_t x) {
    int n = 0;
    while (x) { ++n; x >>= 1; }
    return max(1, n);
}

static inline uint32_t rev_bits(uint32_t x, int w) {
    uint32_t r = 0;
    for (int i = 0; i < w; ++i) {
        r = (r << 1) | ((x >> i) & 1u);
    }

    return r;
}

static inline uint32_t make_pal(uint32_t prefix, int L) {
    int k = L / 2;
    if ((L & 1) == 0) {
        return (prefix << k) | rev_bits(prefix, k);
    } else {
        return (prefix << k) | rev_bits(prefix >> 1, k);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    if (!(cin >> T)) return 0;
    while (T--) {
        uint32_t X;
        cin >> X;

        int n = bitlen(X);
        vector<uint32_t> cand;

        int L = n;
        int m = (L + 1) / 2;
        uint32_t p = (m == L) ? X : (X >> (L - m));
        uint32_t minP = 1u << (m - 1);
        uint32_t maxP = (1u << m) - 1u;

        for (int d = -1; d <= 1; ++d) {
            long long pp = (long long)p + d;
            if (pp >= (long long)minP && pp <= (long long)maxP) {
                cand.push_back(make_pal((uint32_t)pp, L));
            }
        }

        if (n >= 2) cand.push_back((1u << (n - 1)) - 1u);
        cand.push_back((1u << n) | 1u);

        uint32_t best = UINT32_MAX;
        for (uint32_t y : cand) {
            if (y == 0) continue;
            uint32_t diff = (X > y) ? (X - y) : (y - X);
            best = min(best, diff);
        }

        cout << best << '\n';
    }

    return 0;
}