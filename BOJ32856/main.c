#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#define MAXN 20
#define MAXK 400
#define MAXR 4

typedef struct {
    int r, c;
} Cell;

typedef struct {
    int h, w, k;
    Cell cells[MAXK];
} Orientation;

static int cmp_cell(const void *a, const void *b) {
    const Cell *x = (const Cell *)a;
    const Cell *y = (const Cell *)b;
    if (x->r != y->r) return x->r - y->r;
    return x->c - y->c;
}

static void normalize(Orientation *o) {
    int min_r = INT_MAX, min_c = INT_MAX;
    for (int i = 0; i < o->k; ++i) {
        if (o->cells[i].r < min_r) min_r = o->cells[i].r;
        if (o->cells[i].c < min_c) min_c = o->cells[i].c;
    }

    for (int i = 0; i < o->k; ++i) {
        o->cells[i].r -= min_r;
        o->cells[i].c -= min_c;
    }

    qsort(o->cells, (size_t)o->k, sizeof(Cell), cmp_cell);
}

static void rotate90(const Orientation *src, Orientation *dst) {
    dst->h = src->w;
    dst->w = src->h;
    dst->k = src->k;

    for (int i = 0; i < src->k; ++i) {
        int r = src->cells[i].r;
        int c = src->cells[i].c;
        dst->cells[i].r = c;
        dst->cells[i].c = src->h - 1 - r;
    }

    normalize(dst);
}

static int same_orientation(const Orientation *a, const Orientation *b) {
    if (a->h != b->h || a->w != b->w || a->k != b->k) return 0;
    for (int i = 0; i < a->k; ++i) {
        if (a->cells[i].r != b->cells[i].r) return 0;
        if (a->cells[i].c != b->cells[i].c) return 0;
    }
    return 1;
}

int main(void) {
    int r, c;
    scanf("%d %d", &r, &c);

    int land[MAXN][MAXN];
    int total = 0;
    
    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            scanf("%d", &land[i][j]);
            total += land[i][j];
        }
    }

    int s, t;
    scanf("%d %d", &s, &t);

    Orientation start;
    start.h = s;
    start.w = t;
    start.k = 0;

    char row[MAXN + 1];
    for (int i = 0; i < s; ++i) {
        scanf("%s", row);
        for (int j = 0; j < t; ++j) {
            if (row[j] == '#') {
                start.cells[start.k].r = i;
                start.cells[start.k].c = j;
                start.k++;
            }
        }
    }

    normalize(&start);

    Orientation rots[MAXR];
    int rot_count = 0;

    Orientation cur = start;
    for (int rep = 0; rep < 4; ++rep) {
        int exists = 0;
        for (int i = 0; i < rot_count; ++i) {
            if (same_orientation(&rots[i], &cur)) {
                exists = 1;
                break;
            }
        }

        if (!exists) {
            rots[rot_count++] = cur;
        }

        Orientation nxt;
        rotate90(&cur, &nxt);
        cur = nxt;
    }

    int min_cut = INT_MAX;

    for (int idx = 0; idx < rot_count; ++idx) {
        Orientation *o = &rots[idx];

        for (int top = 0; top + o->h <= r; ++top) {
            for (int left = 0; left + o->w <= c; ++left) {
                int cut = 0;
                for (int i = 0; i < o->k; ++i) {
                    int rr = top + o->cells[i].r;
                    int cc = left + o->cells[i].c;
                    cut += land[rr][cc];
                }
                if (cut < min_cut) min_cut = cut;
            }
        }
    }

    printf("%d", total - min_cut);
    return 0;
}