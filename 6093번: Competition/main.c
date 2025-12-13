#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

typedef struct {
    int64_t t;
    int id;
    int lap;
} Node;

static inline int64_t cross_time(int ms, int p, int j) {
    int64_t q = j / p;
    int64_t r = j % p;
    int64_t block = (int64_t)p * (p - 1) / 2;
    int64_t tail = r * (r - 1) / 2;
    return (int64_t)j * ms + q * block + tail;
}

static inline int less_node(const Node *a, const Node *b) {
    if (a->t != b->t) return a->t < b->t;
    return a->id < b->id;
}

typedef struct {
    Node *h;
    int size;
    int cap;
} MinHeap;

static void heap_init(MinHeap *pq, int cap) {
    pq->h = (Node*)malloc((size_t)cap * sizeof(Node));
    pq->size = 0;
    pq->cap = cap;
}

static void heap_free(MinHeap *pq) {
    free(pq->h);
    pq->h = NULL;
    pq->size = pq->cap = 0;
}

static void heap_push(MinHeap *pq, Node x) {
    int i = pq->size++;
    pq->h[i] = x;
    while (i > 0) {
        int p = (i - 1) / 2;
        if (!less_node(&pq->h[i], &pq->h[p])) break;
        Node tmp = pq->h[i];
        pq->h[i] = pq->h[p];
        pq->h[p] = tmp;
        i = p;
    }
}

static Node heap_pop(MinHeap *pq) {
    Node ret = pq->h[0];
    pq->h[0] = pq->h[--pq->size];

    int n = pq->size;
    int i = 0;
    while (1) {
        int l = i * 2 + 1;
        int r = l + 1;
        int m = i;
        if (l < n && less_node(&pq->h[l], &pq->h[m])) m = l;
        if (r < n && less_node(&pq->h[r], &pq->h[m])) m = r;
        if (m == i) break;
        Node tmp = pq->h[i];
        pq->h[i] = pq->h[m];
        pq->h[m] = tmp;
        i = m;
    }

    return ret;
}

int main(void) {
    int K, N;
    if (scanf("%d %d", &K, &N) != 2) return 0;
    int *ms = (int*)malloc((size_t)K * sizeof(int));
    int *p = (int*)malloc((size_t)K * sizeof(int));
    for (int i = 0;  i < K; i++) {
        scanf("%d %d", &ms[i], &p[i]);
    }

    MinHeap pq;
    heap_init(&pq, K);

    for (int i = 0; i < K; i++) {
        Node nd;
        nd.id = i;
        nd.lap = 1;
        nd.t = cross_time(ms[i], p[i], 1);
        heap_push(&pq, nd);
    }

    int64_t prev = -1;
    int run = 0, best = 0;

    while (pq.size > 0) {
        Node cur = heap_pop(&pq);
        if (cur.t == prev) run++;
        else {
            if (run > best) best = run;
            prev = cur.t;
            run = 1;
        }

        if (cur.lap < N) {
            cur.lap++;
            cur.t = cross_time(ms[cur.id], p[cur.id], cur.lap);
            heap_push(&pq, cur);
        }
    }

    if (run > best) best = run;
    printf("%d\n", best);
    heap_free(&pq);
    free(ms);
    free(p);
    return 0;
}