#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static int igcd(int a, int b) {
    if (a < 0) a = -a;
    if (b < 0) b = -b;
    while (b) { int t = a % b; a = b; b = t; }
    return a;
}

typedef struct { int x, y; } Point;

static int point_eq(Point a, Point b) { return a.x == b.x && a.y == b.y; }

static int has_obstacle(const Point *obs, int n, int x, int y) {
    Point p = {x, y};
    for (int i = 0; i < n; i++) if (point_eq(obs[i], p)) return 1;
    return 0;
}

static int safe_move(const Point *obs, int n, int x, int y, int dx, int dy) {
    if (dx == 0 && dy == 0) {
        return !has_obstacle(obs, n, x, y);
    }
    int g = igcd(dx, dy);
    int stepx = dx / g;
    int stepy = dy / g;
    for (int i = 1; i <= g; i++) {
        int px = x + stepx * i;
        int py = y + stepy * i;
        if (has_obstacle(obs, n, px, py)) return 0;
    }

    return 1;
}

int main(void) {
    int tx, ty, N;
    if (scanf("%d %d %d", &tx, &ty, &N) != 3) return 0;

    Point obs[105];
    for (int i = 0; i < N; i++) scanf("%d %d", &obs[i].x, &obs[i].y);

    if (has_obstacle(obs, N, 0, 0)) {
        printf("-1\n");
        return 0;
    }

    if (tx == 0 && ty == 0) {
        printf("0\n");
        return 0;
    }

    if (has_obstacle(obs, N, tx, ty)) {
        printf("-1\n");
        return 0;
    }

    const int L = 60;
    const int W = 2 * L + 1;
    const int V = 15;
    const int STATES = W * W * V * V;

    int *dist = (int*)malloc((size_t)STATES * sizeof(int));
    if (!dist) return 0;
    for (int i = 0; i < STATES; i++) dist[i] = -1;
    int *q = (int*)malloc((size_t)STATES * sizeof(int));
    if (!q) { free(dist); return 0; }
    int qh = 0, qt = 0;

    #define IDX(x,y,vx,vy) ( \
        (((( ( (x) + L) * W + ((y) + L) ) * V + ((vx) + 7) ) * V + ((vy) + 7))) \
    )

    int start_idx = IDX(0,0,0,0);
    dist[start_idx] = 0;
    q[qt++] = start_idx;

    while (qh < qt) {
        int cur = q[qh++];
        int t = dist[cur];
        int tmp = cur;
        int vy_i = tmp % V; tmp /= V;
        int vx_i = tmp % V; tmp /= V;
        int pos = tmp;
        int y_i = pos % W;
        int x_i = pos / W;

        int x = x_i - L;
        int y = y_i - L;
        int vx = vx_i - 7;
        int vy = vy_i - 7;

        for (int a = 0; a < 5; a++) {
            int nvx = vx, nvy = vy;
            if (a == 1) {
                if (nvx == 7) continue;
                nvx++;
            } else if (a == 2) {
                if (nvx == -7) continue;
                nvx--;
            } else if (a == 3) {
                if (nvy == 7) continue;
                nvy++;
            } else if (a == 4) {
                if (nvy == -7) continue;
                nvy--;
            }

            int nx = x + nvx;
            int ny = y + nvy;

            if (nx < -L || nx > L || ny < -L || ny > L) continue;
            if (!safe_move(obs, N, x, y, nvx, nvy)) continue;

            int ni = IDX(nx, ny, nvx, nvy);
            if (dist[ni] != -1) continue;

            dist[ni] = t + 1;
            if (nx == tx && ny == ty) {
                printf("%d\n", dist[ni]);
                free(q);
                free(dist);
                return 0;
            }
            q[qt++] = ni;
        }
    }

    printf("-1\n");
    free(q);
    free(dist);
    return 0;
}