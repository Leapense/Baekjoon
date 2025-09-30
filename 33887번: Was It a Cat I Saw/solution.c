#include <stdio.h>
#include <stdint.h>

static inline uint32_t bitlen(uint32_t x) {
    int n = 0;
    while (x) { ++n; x >>= 1; }
    return n > 0 ? (uint32_t)n : 1u;
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

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;
    while (T--) {
        uint32_t X;
        if (scanf("%u", &X) != 1) return 0;

        int n = (int)bitlen(X);
        uint32_t candidates[6];
        int cnt = 0;

        int L = n;
        int m = (L + 1) / 2;
        uint32_t p = (m == L) ? X : (X >> (L - m));
        uint32_t minP = 1u << (m - 1);
        uint32_t maxP = (1u << m) - 1u;

        for (int d = -1; d <= 1; ++d) {
            int64_t pp = (int64_t)p + d;
            if (pp >= (int64_t)minP && pp <= (int64_t)maxP) {
                candidates[cnt++] = make_pal((uint32_t)pp, L);
            }
        }

        if (n >= 2) candidates[cnt++] = (1u << (n - 1)) - 1u;
        candidates[cnt++] = (1u << n) | 1u;

        uint32_t best = UINT32_MAX;
        for (int i = 0; i < cnt; ++i) {
            uint32_t y = candidates[i];
            if (y == 0) continue;
            uint32_t diff = (X > y) ? (X - y) : (y - X);
            if (diff < best) best = diff;
        }

        printf("%u\n", best);
    }

    return 0;
}