#include <stdio.h>

int main(void) {
    int n;
    long long r, x;
    if (scanf("%d %lld %lld", &n, &r, &x) != 3) return 0;

    long long cur = 0;
    long long ans = 0;

    for (int i = 0; i < n; i++) {
        long long a;
        scanf("%lld", &a);

        cur += a;

        if (cur > r) cur = r;
        if (cur <= x) {
            ans += cur;
            cur = 0;
        } else {
            ans += x;
            cur -= x;
        }
    }

    printf("%lld\n", ans);
    return 0;
}