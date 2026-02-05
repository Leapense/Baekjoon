#include <stdio.h>
#include <stdint.h>

static unsigned floor_log2_u32(unsigned x) {
    unsigned k = 0;
    while (x >>= 1) ++k;
    return k;
}

static void to_binary_u32(unsigned x, char out[64]) {
    int msb = 0;
    unsigned t = x;
    while (t >>= 1) ++msb;

    int p = 0;
    for (int i = msb; i >= 0; --i) {
        out[p++] = ((x >> i) & 1u) ? '1' : '0';
    }

    out[p] = '\0';
}

int main(void) {
    unsigned n;

    while (scanf("%u", &n) == 1 && n != 0u) {
        char parts[64][64];
        int cnt = 0;

        unsigned x = n;
        while (x > 1u) {
            to_binary_u32(x, parts[cnt++]);
            x = floor_log2_u32(x);
        }

        putchar('0');
        for (int i = cnt - 1; i >= 0; --i) {
            fputs(parts[i], stdout);
        }

        putchar('\n');
    }

    return 0;
}