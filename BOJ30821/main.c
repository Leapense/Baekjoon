#include <stdio.h>

int main(void) {
    long long N;
    if (scanf("%lld", &N) != 1) return 0;
    long long ans = N * (N - 1) * (N - 2) * (N - 3) * (N - 4) / 120;
    printf("%lld\n", ans);
    return 0;
}