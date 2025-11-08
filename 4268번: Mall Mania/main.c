#include <stdio.h>
#include <string.h>

#define LIM 2000
#define N 2001
typedef struct { short x, y; } Node;

static unsigned char vis[N][N];
static unsigned char isB[N][N];
static Node q[N * N + 5];

int main(void) {
    int p1;
    while (scanf("%d", &p1) == 1) {
        if (p1 == 0) break;

        Node *A = (Node*)malloc(sizeof(Node) * p1);
        for (int i = 0; i < p1; ++i) {
            int a, s;
            scanf("%d %d", &a, &s);
            A[i].x = (short)a;
            A[i].y = (short)s;
        }

        int p2;
        scanf("%d", &p2);
        Node *B = (Node*)malloc(sizeof(Node) * p2);
        for (int i = 0; i < p2; ++i) {
            int a, s;
            scanf("%d %d", &a, &s);
            B[i].x = (short)a; B[i].y = (short)s;
        }
        memset(vis, 0, sizeof(vis));
        memset(isB, 0, sizeof(isB));

        for (int i = 0; i < p2; ++i) {
            int x = B[i].x, y = B[i].y;
            if (0 <= x && x <= LIM && 0 <= y && y <= LIM) {
                isB[y][x] = 1;
            }
        }

        int head = 0, tail = 0;
        int immediate = 0;

        for (int i = 0; i < p1; ++i) {
            int x = A[i].x, y = A[i].y;
            if (x < 0 || x > LIM || y < 0 || y > LIM) continue;
            if (!vis[y][x]) {
                vis[y][x] = 1;
                q[tail++] = (Node){(short)x, (short)y};
                if (isB[y][x]) {
                    printf("0\n");
                    immediate = 1;
                    break;
                }
            }
        }

        if (!immediate) {
            const int dx[4] = {1,-1,0,0};
            const int dy[4] = {0,0,1,-1};
            int dist = 0;
            while (head < tail) {
                int layer = tail - head;
                for (int i = 0; i < layer; ++i) {
                    Node cur = q[head++];

                    for (int d = 0; d < 4; ++d) {
                        int nx = cur.x + dx[d], ny = cur.y + dy[d];
                        if (nx < 0 || nx > LIM || ny < 0 || ny > LIM) continue;
                        if (vis[ny][nx]) continue;
                        if (isB[ny][nx]) {
                            printf("%d\n", dist + 1);
                            goto done_case;
                        }
                        vis[ny][nx] = 1;
                        q[tail++] = (Node){(short)nx, (short)ny};
                    }
                }
                ++dist;
            }

            printf("%d\n", dist);
        }
        done_case:
        free(A); free(B);
    }

    return 0;
}