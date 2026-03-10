#include <stdio.h>

typedef unsigned long long ull;
typedef unsigned __int128 u128;

static void print_u128(u128 x) {
    if (x == 0) {
        putchar('0');
        return;
    }

    char buf[50];
    int len = 0;

    while (x > 0) {
        buf[len++] = (char)('0' + (x % 10));
        x /= 10;
    }

    while (len--) putchar(buf[len]);
}

int main(void) {
    ull a, b, c;
    if (scanf("%llu %llu %llu", &a, &b, &c) != 3) {
        return 0;
    }

    u128 answer = (u128)a + (u128)b * (u128)(a - a / c);
    print_u128(answer);
    return 0;
}