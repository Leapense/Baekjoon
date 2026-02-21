#include <stdio.h>
#include <stdint.h>

#define BUF_SIZE (1 << 20)

static unsigned char buf[BUF_SIZE];
static unsigned char *ptr = buf;
static unsigned char *end = buf;

static inline int read_int(void) {
    int x = 0;
    while (1) {
        if (ptr >= end) {
            size_t sz = fread(buf, 1, BUF_SIZE, stdin);
            if (sz == 0) return -1;
            ptr = buf;
            end = buf + sz;
        }
        if (*ptr > ' ') break;
        ptr++;
    }

    while (1) {
        while (ptr < end && *ptr > ' ') {
            x = x * 10 + (*ptr++ - '0');
        }

        if (ptr < end) break;

        size_t sz = fread(buf, 1, BUF_SIZE, stdin);
        if (sz == 0) break;
        ptr = buf;
        end = buf + sz;
    }
    return x;
}

int main(void) {
    int N = read_int();
    if (N <= 0) return 0;

    int prev = read_int();
    if (prev < 0) return 0;

    int64_t ans = (int64_t) N;

    int L0 = prev - 1;
    ans += (int64_t)(L0 + 1) >> 1;

    for (int i = 2; i <= N; i++) {
        int x = read_int();
        if (x < 0) return 0;

        int diff = x - prev;
        if (diff == 1) {
            puts("-1");
            return 0;
        }

        int Li = diff - 2;
        ans += (int64_t)(Li + 1) >> 1;

        prev = x;
    }

    printf("%lld\n", (long long)ans);
    return 0;
}