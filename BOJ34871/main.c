#include <stdio.h>
#include <stdint.h>

#define IN_BUF_SIZE (1 << 20)
#define OUT_BUF_SIZE (1 << 20)

typedef struct {
    char buf[IN_BUF_SIZE];
    size_t idx, size;
} FastScanner;

static inline char read_char(FastScanner *fs) {
    if (fs->idx >= fs->size) {
        fs->size = fread(fs->buf, 1, IN_BUF_SIZE, stdin);
        fs->idx = 0;
        if (fs->size == 0) return 0;
    }
    return fs->buf[fs->idx++];
}

static int next_ll(FastScanner *fs, long long *out) {
    char c = read_char(fs);
    long long sign = 1;
    long long val = 0;

    if (!c) return 0;

    while (c != '-' && (c < '0' || c > '9')) {
        c = read_char(fs);
        if (!c) return 0;
    }

    if (c == '-') {
        sign = -1;
        c = read_char(fs);
    }

    while (c >= '0' && c <= '9') {
        val = val * 10 + (c - '0');
        c = read_char(fs);
    }

    *out = val * sign;
    return 1;
}

typedef struct {
    char buf[OUT_BUF_SIZE];
    size_t idx;
} FastOutput;

static inline void flush_output(FastOutput *fo) {
    if (fo->idx > 0) {
        fwrite(fo->buf, 1, fo->idx, stdout);
        fo->idx = 0;
    }
}

static inline void push_char(FastOutput *fo, char c) {
    if (fo->idx == OUT_BUF_SIZE) flush_output(fo);
    fo->buf[fo->idx++] = c;
}

static void write_ll(FastOutput *fo, long long x) {
    if (x == 0) {
        push_char(fo, '0');
        push_char(fo, '\n');
        return;
    }

    if (x < 0) {
        push_char(fo, '-');
        x = -x;
    }

    char s[32];
    int n = 0;
    while (x > 0) {
        s[n++] = (char)('0' + (x % 10));
        x /= 10;
    }
    while (n--) push_char(fo, s[n]);
    push_char(fo, '\n');
}

int main(void) {
    FastScanner fs = { .idx = 0, .size = 0 };
    FastOutput fo = { .idx = 0 };

    long long n, q;
    next_ll(&fs, &n);
    next_ll(&fs, &q);

    long long Lmax = 0;
    long long Rmin = (long long)4e18;

    for (long long i = 0; i < n; ++i) {
        long long l, r, y;
        next_ll(&fs, &l);
        next_ll(&fs, &r);
        next_ll(&fs, &y);

        if (l > Lmax) Lmax = l;
        if (r < Rmin) Rmin = r;
    }

    for (long long i = 0; i < q; ++i) {
        long long p;
        next_ll(&fs, &p);

        long long ans = 0;
        if (Lmax - p > ans) ans = Lmax - p;
        if (p - Rmin > ans) ans = p - Rmin;

        write_ll(&fo, ans);
    }
    flush_output(&fo);
    return 0;
}