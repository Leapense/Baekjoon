#include <stdio.h>

int main(void) {
    long long g, gb, y, r, ry;
    long long T;
    if (scanf("%lld %lld %lld %lld %lld", &g, &gb, &y, &r, &ry) != 5) return 0;
    if (scanf("%lld", &T) != 1) return 0;

    long long red2 = 0, yellow2 = 0, green2 = 0;
    long long rem = T;

    while (rem > 0) {
        long long x;
        x = (rem < g ? rem : g);
        green2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = (rem < gb ? rem : gb);
        green2 += x;
        rem -= x;
        if (rem == 0) break;

        x = (rem < y ? rem : y);
        yellow2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = (rem < r ? rem : r);
        red2 += 2 * x;
        rem -= x;
        if (rem == 0) break;

        x = (rem < ry ? rem : ry);
        red2 += 2 * x;
        yellow2 += 2 * x;
        rem -= x;
    }

    printf("%lld %lld %lld\n", red2 / 2, yellow2 / 2, green2 / 2);
    return 0;
}