#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <stdlib.h>

static int64_t parse_scaled_1e5(const char *s) {
    int sign = 1;
    if (*s == '-') { sign = -1; s++; }
    else if (*s == '+') { s++; }

    int64_t ip = 0;
    while (*s && *s != '.' && *s != '\n' && *s != '\r') {
        if (*s >= '0' && *s <= '9') {
            ip = ip * 10 + (*s - '0');
        }
        s++;
    }

    int64_t frac = 0;
    int frac_len = 0;
    if (*s == '.') {
        s++;
        while (*s && *s != '\n' && *s != '\r' && frac_len < 5) {
            if (*s >= '0' && *s <= '9') {
                frac = frac * 10 + (*s - '0');
                frac_len++;
            }
            s++;
        }
    }

    while (frac_len < 5) { frac *= 10; frac_len++; }
    int64_t scaled = ip * 100000LL + frac;
    return (int64_t)sign * scaled;
}

static __int128 i128_abs(__int128 x) { return x < 0 ? -x : x; }

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;
    if (N == 0) {
        printf("0\n");
        return 0;
    }

    int64_t *a = (int64_t*)malloc((size_t)N * sizeof(int64_t));
    if (!a) return 0;

    int64_t sum = 0;
    char buf[128];

    for (int i = 0; i < N; i++) {
        if (scanf("%127s", buf) != 1) { free(a); return 0; }
        a[i] = parse_scaled_1e5(buf);
        sum += a[i];
    }

    const int64_t TH = 1000000LL;
    int ans = 0;

    for (int i = 0; i < N; i++) {
        __int128 left = (__int128)a[i] * (__int128)N - (__int128)sum;
        __int128 dist = i128_abs(left);
        __int128 bound = (__int128)TH * (__int128)N;
        if (dist > bound) ans++;
    }

    printf("%d\n", ans);
    free(a);
    return 0;
}