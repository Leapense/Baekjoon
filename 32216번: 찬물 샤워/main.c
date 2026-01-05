#include <stdio.h>

int main(void) {
    int n, k, T0;
    if (scanf("%d %d %d", &n, &k, &T0) != 3) return 0;

    long long ans = 0;
    for (int i = 0; i < n; i++) {
        int d;
        scanf("%d", &d);
        ans += (long long)d;
    }

    printf("%lld\n", ans);
    return 0;
}