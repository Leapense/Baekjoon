#include <stdio.h>
#include <stdlib.h>

typedef long long ll;
typedef struct {
    ll L, R;
} Interval;

int cmpL(const void* a, const void* b) {
    const Interval* x = (const Interval*)a;
    const Interval* y = (const Interval*)b;
    return (x->L < y->L) ? -1 : (x->L > y->L);
}

int cmpLL(const void* a, const void* b) {
    const ll* x = (const ll*)a;
    const ll* y = (const ll*)b;
    return (*x < *y) ? -1 : (*x > *y);
}

typedef struct {
    int n;
    int* bit;
} Fenwick;

Fenwick fw_create(int n) {
    Fenwick f;
    f.n = n;
    f.bit = (int*)calloc(n + 1, sizeof(int));
    return f;
}

void fw_add(Fenwick* f, int i, int v) {
    for (; i <= f->n; i += i & -i) f->bit[i] += v;
}

int fw_sum(const Fenwick* f, int i) {
    int s = 0;
    for (; i > 0; i -= i & -i) s += f->bit[i];
    return s;
}

void fw_free(Fenwick* f) {
    free(f->bit);
    f->bit = NULL;
    f->n = 0;
}

int lb_ll(const ll* a, int n, ll key) {
    int lo = 0, hi = n;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < key) lo = mid + 1;
        else hi = mid;
    }
    return lo;
}

int main(void)
{
    int N;
    if (scanf("%d", &N) != 1) return 0;

    Interval* seg = (Interval*)malloc(sizeof(Interval) * (size_t)N);
    ll* rights = (ll*)malloc(sizeof(ll) * (size_t)N);

    for (int i = 0; i < N; ++i) {
        scanf("%lld %lld", &seg[i].L, &seg[i].R);
        rights[i] = seg[i].R;
    }

    qsort(seg, N, sizeof(Interval), cmpL);
    qsort(rights, N, sizeof(ll), cmpLL);
    int M = 0;
    for (int i = 0; i < N; ++i) {
        if (i == 0 || rights[i] != rights[i - 1]) rights[M++] = rights[i];
    }

    Fenwick fw = fw_create(M);
    int best = 0;
    for (int i = N - 1; i >= 0; --i) {
        int r = lb_ll(rights, M, seg[i].R) + 1;
        int cnt = fw_sum(&fw, r - 1);
        if (cnt > best) best = cnt;
        fw_add(&fw, r, 1);
    }

    printf("%d\n", best);
    fw_free(&fw);
    free(rights);
    free(seg);
    return 0;
}