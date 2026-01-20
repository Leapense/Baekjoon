#include <stdio.h>

int main(void) {
    long long a, b;
    if (scanf("%lld %lld", &a, &b) != 2) return 0;

    long long min_n = (a + 1) / 2;
    long long tmp = (b + 1) / 2;
    if (tmp > min_n) min_n = tmp;
    long long max_n = (a < b) ? a : b;
    printf("%lld %lld\n", min_n, max_n);
    return 0;
}