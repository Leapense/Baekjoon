#include <stdio.h>

static long long ceil_div(long long x, long long y) {
    return (x + y - 1) / y;
}

int main(void) {
    long long a, b, h, w;
    if (scanf("%lld %lld %lld %lld", &a, &b, &h, &w) != 4) return 0;

    long long k = ceil_div(h, a);
    long long l = ceil_div(w, b);

    printf("%lld\n", k * l);
    return 0;
}