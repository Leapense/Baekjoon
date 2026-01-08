#include <stdio.h>
#include <stdint.h>

static inline int fast_read_int(int *out) {
    int c = getchar_unlocked();
    while (c != EOF && c <= ' ') c = getchar_unlocked();
    if (c == EOF) return 0;

    int sign = 1;
    if (c == '-') { sign = -1; c = getchar_unlocked(); }

    int x = 0;
    while (c > ' ') {
        x = x * 10 + (c - '0');
        c = getchar_unlocked();
    }
    *out = x * sign;
    return 1;
}

static inline int fast_read_ll(long long * out) {
    int c = getchar_unlocked();
    while (c != EOF && c <= ' ') c = getchar_unlocked();
    if (c == EOF) return 0;

    long long sign = 1;
    if (c == '-') { sign = -1; c = getchar_unlocked(); }

    long long x = 0;
    while (c > ' ') {
        x = x * 10 + (c - '0');
        c = getchar_unlocked();
    }

    *out = x * sign;
    return 1;
}

int main(void) {
    int n;
    if (!fast_read_int(&n)) return 0;

    long long cur = 0, ans = 0;

    for (int i = 0; i < n; i++) {
        long long a, b;
        fast_read_ll(&a);
        fast_read_ll(&b);

        cur -= a;
        cur += b;
        if (cur > ans) ans = cur;
    }

    printf("%lld\n", ans);
    return 0;
}