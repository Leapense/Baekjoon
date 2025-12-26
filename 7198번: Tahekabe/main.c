#include <stdio.h>
#include <string.h>
#include <stdbool.h>

static int N;
static char board[20][21];
static bool vis[20][20];
static const int dr[4] = {-1, 1, 0, 0};
static const int dc[4] = {0, 0, -1, 1};

static bool dfs(int r, int c, const char *w, int idx, int len) {
    if (idx == len) return true;
    for (int d = 0; d < 4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
        if (vis[nr][nc]) continue;
        if (board[nr][nc] != w[idx]) continue;

        vis[nr][nc] = true;
        if (dfs(nr, nc, w, idx + 1, len)) return true;
        vis[nr][nc] = false;
    }

    return false;
}

static bool can_make_word(int sr, int sc, const char *w) {
    int len = (int)strlen(w);
    memset(vis, 0, sizeof(vis));

    for (int d = 0; d < 4; d++) {
        int nr = sr + dr[d];
        int nc = sc + dc[d];
        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
        if (board[nr][nc] != w[0]) continue;
        vis[nr][nc] = true;
        if (dfs(nr, nc, w, 1, len)) return true;
        vis[nr][nc] = false;
    }
    return false;
}

int main(void) {
    int R, V;
    if (scanf("%d %d %d", &N, &R, &V) != 3) return 0;
    int sr = R - 1, sc = V - 1;

    for (int i = 0; i < N; i++) {
        scanf("%20s", board[i]);
    }

    int K;
    scanf("%d", &K);

    bool any = false;
    for (int i = 0; i < K; i++) {
        char w[32];
        scanf("%31s", w);

        if (can_make_word(sr, sc, w)) {
            puts(w);
            any = true;
        }
    }

    if (!any) {
        puts("EI SAA");
    }

    return 0;
}