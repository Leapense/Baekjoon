#include <stdio.h>
#include <stdlib.h>

static long long llabs_ll(long long x) { return x < 0 ? -x : x; }

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    long long L = (long long)1e18;
    long long R = (long long)-1e18;
    for (int i = 1; i <= n; i++) {
        int a;
        scanf("%d", &a);
        long long d = (long long)a - (long long)i;
        if (d < L) L = d;
        if (d > R) R = d;
    }

    long long ans = (R - L) + (llabs_ll(L) < llabs_ll(R) ? llabs_ll(L) : llabs_ll(R));
    printf("%lld\n", ans);
    return 0;
}