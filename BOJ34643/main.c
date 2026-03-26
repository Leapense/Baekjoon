#include <stdio.h>
#include <stdlib.h>

static int cmp_desc(const void *x, const void *y) {
    long long a = *(const long long *)x;
    long long b = *(const long long *)y;
    if (a < b) return 1;
    if (a > b) return -1;
    return 0;
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    long long a[11], s[10], pref[11];
    for (int i = 1; i <= n; ++i) scanf("%lld", &a[i]);
    for (int i = 0; i < n; ++i) scanf("%lld", &s[i]);

    qsort(s, n, sizeof(long long), cmp_desc);

    pref[0] = 0;
    for (int i = 1; i <= n; ++i) {
        pref[i] = pref[i - 1] + s[i - 1];
    }

    long double ans = 0.0L;
    for (int k = 1; k <= n; ++k) {
        long double cur = ((long double)pref[k] + (long double)a[k]) / (long double)k;
        if (cur > ans) ans = cur;
    }

    printf("%.10Lf", ans);
    return 0;
}