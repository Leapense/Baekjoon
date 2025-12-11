#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct {
    int to;
    int cost;
    int next;
} Edge;

typedef struct {
    int v;
    long long dist;
} HeapNode;

const long long INF = 4000000000000000000LL;

void heap_swap(HeapNode *a, HeapNode *b) {
    HeapNode tmp = *a;
    *a = *b;
    *b = tmp;
}

void heap_push(HeapNode *heap, int *heap_size, HeapNode node) {
    int i = ++(*heap_size);
    heap[i] = node;
    while (i > 1) {
        int parent = i / 2;
        if (heap[parent].dist <= heap[i].dist) break;
        heap_swap(&heap[parent], &heap[i]);
        i = parent;
    }
}

HeapNode heap_pop(HeapNode *heap, int *heap_size) {
    HeapNode top = heap[1];
    heap[1] = heap[*heap_size];
    (*heap_size)--;
    int i = 1;
    while (1) {
        int left = i * 2;
        int right = i * 2 + 1;
        int smallest = i;
        if (left <= *heap_size && heap[left].dist < heap[smallest].dist) {
            smallest = left;
        }
        if (right <= *heap_size && heap[right].dist < heap[smallest].dist) {
            smallest = right;
        }
        if (smallest == i) break;
        heap_swap(&heap[i], &heap[smallest]);
        i = smallest;
    }
    return top;
}

int main(void) {
    int T, C, Ts, Te;
    if (scanf("%d %d %d %d", &T, &C, &Ts, &Te) != 4) {
        return 0;
    }
    int m = 2 * C;
    Edge *edges = (Edge *)malloc((m) * (sizeof(Edge)));
    int *head = (int *)malloc((T + 1) * sizeof(int));

    if (!edges || !head) {
        return 0;
    }
    for (int i = 1; i <= T; ++i) {
        head[i] = -1;
    }

    int idx = 0;
    for (int i = 0; i < C; ++i) {
        int a, b, c;
        scanf("%d %d %d", &a, &b, &c);
        edges[idx].to = b;
        edges[idx].cost = c;
        edges[idx].next = head[a];
        head[a] = idx++;
        edges[idx].to = a;
        edges[idx].cost = c;
        edges[idx].next = head[b];
        head[b] = idx++;
    }

    long long *dist = (long long *)malloc((T + 1) * sizeof(long long));
    bool *visited = (bool *)malloc((T + 1) * sizeof(bool));
    if (!dist || !visited) {
        return 0;
    }

    for (int i = 1; i <= T; ++i) {
        dist[i] = INF;
        visited[i] = false;
    }

    dist[Ts] = 0;
    int heap_max = 2 * C + 5;
    HeapNode *heap = (HeapNode *)malloc((heap_max + 1) * sizeof(HeapNode));
    int heap_size = 0;

    HeapNode start_node = { Ts, 0 };
    heap_push(heap, &heap_size, start_node);

    while (heap_size > 0) {
        HeapNode cur = heap_pop(heap, &heap_size);
        int u = cur.v;
        long long d = cur.dist;

        if (visited[u]) continue;
        visited[u] = true;

        if (u == Te) break;

        for (int e = head[u]; e != -1; e = edges[e].next) {
            int v = edges[e].to;
            int w = edges[e].cost;
            if (visited[v]) continue;
            long long nd = d + (long long)w;
            if (nd < dist[v]) {
                dist[v] = nd;
                HeapNode nxt = {v, nd};
                heap_push(heap, &heap_size, nxt);
            }
        }
    }

    printf("%lld\n", dist[Te]);

    free(edges);
    free(head);
    free(dist);
    free(visited);
    free(heap);

    return 0;
}