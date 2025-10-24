#include <stdio.h>
#include <string.h>

int main(void) {
    unsigned long long K;
    if (scanf("%llu", &K) != 1) return 0;

    unsigned long long x = K + 1ULL;
    char buf[70];
    int n = 0;

    while (x > 1ULL) {
        buf[n++] = (x & 1ULL) ? '7' : '4';
        x >>= 1;
    }

    for (int i = n - 1; i >= 0; --i) putchar(buf[i]);
    putchar('\n');
    return 0;
}