#include <stdio.h>
#include <stdbool.h>

typedef struct {
    int dx;
    int dy;
} Vec;

static void copy_vec(Vec *dst, const Vec *src, int len) {
    for (int i = 0; i < len; ++i) {
        dst[i] = src[i];
    }
}

static void rotate90(Vec *v, int len) {
    for (int i = 0; i < len; ++i) {
        int dx = v[i].dx;
        int dy = v[i].dy;
        v[i].dx = -dy;
        v[i].dy = dx;
    }
}

static void reverse_vec(Vec *dst, const Vec *src, int len) {
    for (int i = 0; i < len; ++i) {
        dst[i].dx = -src[len - 1 - i].dx;
        dst[i].dy = -src[len - 1 - i].dy;
    }
}

static bool less_seq(const Vec *a, const Vec *b, int len) {
    for (int i = 0; i < len; ++i) {
        if (a[i].dx != b[i].dx) return a[i].dx < b[i].dx;
        if (a[i].dy != b[i].dy) return a[i].dy < b[i].dy;
    }

    return false;
}

static bool equal_seq(const Vec *a, const Vec *b, int len) {
    for (int i = 0; i < len; ++i) {
        if (a[i].dx != b[i].dx || a[i].dy != b[i].dy) return false;
    }

    return true;
}

static void canonical(const Vec *edge, int len, Vec *out) {
    Vec rot[9];
    Vec rev[9];
    Vec best[9];
    bool has_best = false;
    copy_vec(rot, edge, len);

    for (int k = 0; k < 4; ++k) {
        if (!has_best || less_seq(rot, best, len)) {
            copy_vec(best, rot, len);
            has_best = true;
        }

        reverse_vec(rev, rot, len);
        if (less_seq(rev, best, len)) {
            copy_vec(best, rev, len);
        }

        rotate90(rot, len);
    }
    copy_vec(out, best, len);
}

int main(void) {
    int n;
    while (1) {
        if (scanf("%d", &n) != 1) return 0;
        if (n == 0) break;

        int m0;
        scanf("%d", &m0);
        int x_prev, y_prev;
        int x, y;

        Vec edges0[9];
        int L0 = m0 - 1;

        scanf("%d %d", &x_prev, &y_prev);
        for (int i = 1; i < m0; ++i) {
            scanf("%d %d", &x, &y);
            edges0[i - 1].dx = x - x_prev;
            edges0[i - 1].dy = y - y_prev;
            x_prev = x;
            y_prev = y;
        }

        Vec canon0[9];
        canonical(edges0, L0, canon0);

        for (int idx = 1; idx <= n; ++idx) {
            int mi;
            scanf("%d", &mi);

            int px_prev, py_prev;
            int px, py;
            Vec edges[9];
            int Li = mi - 1;

            scanf("%d %d", &px_prev, &py_prev);
            for (int i = 1; i < mi; ++i) {
                scanf("%d %d", &px, &py);
                if (Li == L0) {
                    edges[i - 1].dx = px - px_prev;
                    edges[i - 1].dy = py - py_prev;
                }

                px_prev = px;
                py_prev = py;
            }

            if (Li != L0) {
                continue;
            }

            Vec canoni[9];
            canonical(edges, Li, canoni);

            if (equal_seq(canon0, canoni, L0)) {
                printf("%d\n", idx);
            }
        }

        printf("+++++\n");
    }

    return 0;
}