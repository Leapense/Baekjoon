#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BUFSIZE (1u << 20)

typedef struct {
    unsigned char buf[BUFSIZE];
    size_t idx, size;
} FastInput;

static inline unsigned char read_byte(FastInput *in) {
    if (in->idx >= in->size) {
        in->size = fread(in->buf, 1, BUFSIZE, stdin);
        in->idx = 0;
        if (in->size == 0) return 0;
    }
    return in->buf[in->idx++];
}

static int read_int(FastInput *in, int *out) {
    unsigned char c;
    do {
        c = read_byte(in);
        if (!c) return 0;
    } while (c <= ' ');

    int neg = 0;
    if (c == '-') {
        neg = 1;
        c = read_byte(in);
    }

    int val = 0;
    while (c > ' ') {
        val = val * 10 + (int)(c - '0');
        c = read_byte(in);
    }
    *out = neg ? -val : val;
    return 1;
}

typedef struct {
    int *p;
    unsigned char *r;
    int n;
} DSU;

static void dsu_init(DSU *d, int n) {
    d->n = n;
    d->p = (int*)malloc((size_t)n * sizeof(int));
    d->r = (unsigned char*)malloc((size_t)n * sizeof(unsigned char));
    for (int i = 0; i < n; i++) {
        d->p[i] = i;
        d->r[i] = 0;
    }
}

static void dsu_free(DSU *d) {
    free(d->p);
    free(d->r);
    d->p = NULL;
    d->r = NULL;
    d->n = 0;
}

static int dsu_find(DSU *d, int x) {
    while (d->p[x] != x) {
        d->p[x] = d->p[d->p[x]];
        x = d->p[x];
    }
    return x;
}

static void dsu_unite(DSU *d, int a, int b) {
    a = dsu_find(d, a);
    b = dsu_find(d, b);
    if (a == b) return;

    if (d->r[a] < d->r[b]) {
        int tmp = a; a = b; b = tmp;
    }
    d->p[b] = a;
    if (d->r[a] == d->r[b]) d->r[a]++;
}

typedef struct {
    char *s;
    size_t len, cap;
} StrBuf;

static void sb_init(StrBuf *sb, size_t cap) {
    sb->s = (char*)malloc(cap);
    sb->len = 0;
    sb->cap = cap;
}

static void sb_ensure(StrBuf *sb, size_t add) {
    if (sb->len + add <= sb->cap) return;
    size_t ncap = sb->cap;
    while (sb->len + add > ncap) ncap <<= 1;
    sb->s = (char*)realloc(sb->s, ncap);
    sb->cap = ncap;
}

static void sb_append(StrBuf *sb, const char *t) {
    size_t L = strlen(t);
    sb_ensure(sb, L);
    memcpy(sb->s + sb->len, t, L);
    sb->len += L;
}

static void sb_append_int(StrBuf *sb, int x) {
    char buf[32];
    int n = snprintf(buf, sizeof(buf), "%d", x);
    if (n < 0) return;
    sb_ensure(sb, (size_t)n);
    memcpy(sb->s + sb->len, buf, (size_t)n);
    sb->len += (size_t)n;
}

static void sb_append_char(StrBuf *sb, char c) {
    sb_ensure(sb, 1);
    sb->s[sb->len++] = c;
}

static void sb_free(StrBuf *sb) {
    free(sb->s);
    sb->s = NULL;
    sb->len = sb->cap = 0;
}

int main(void) {
    FastInput in = {.idx = 0, .size = 0};

    int T;
    if (!read_int(&in, &T)) return 0;

    StrBuf out;
    sb_init(&out, 1u << 22);

    for (int tc = 1; tc <= T; tc++) {
        int n; read_int(&in, &n);
        DSU dsu; dsu_init(&dsu, n);

        int k; read_int(&in, &k);
        for (int i = 0; i < k; i++) {
            int a, b;
            read_int(&in, &a);
            read_int(&in, &b);
            dsu_unite(&dsu, a, b);
        }
        int m; read_int(&in, &m);

        sb_append(&out, "Scenario ");
        sb_append_int(&out, tc);
        sb_append(&out, ":\n");

        for (int i = 0; i < m; i++) {
            int u, v;
            read_int(&in, &u);
            read_int(&in, &v);
            sb_append_char(&out, (dsu_find(&dsu, u) == dsu_find(&dsu, v)) ? '1' : '0');
            sb_append_char(&out, '\n');
        }
        sb_append_char(&out, '\n');
        dsu_free(&dsu);
    }

    fwrite(out.s, 1, out.len, stdout);
    sb_free(&out);
    return 0;
}