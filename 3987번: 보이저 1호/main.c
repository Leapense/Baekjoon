#include <stdio.h>
#include <stdbool.h>

enum { U=0, R=1, D=2, L=3 };

static int N, M, PR, PC;
static char g[500][501];
static unsigned char vis[500][500][4];

const int dr[4] = {-1, 0, 1, 0};
const int dc[4] = {0, 1, 0, -1};
const int slash_reflect[4] = {R, U, L, D};
const int back_reflect[4]  = {L, D, R, U};
const char dirChar[4] = {'U','R','D','L'};

static void simulate(int d, bool *isInf, long long *ftime) {
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j)
            for (int k = 0; k < 4; ++k)
                vis[i][j][k] = 0;

    long long t = 0;
    int r = PR, c = PC, dir = d;
    while (1) {
        r += dr[dir]; c += dc[dir]; ++t;
        if (r < 0 || r >= N || c < 0 || c >= M) { *isInf = false; *ftime = t; return; }
        char ch = g[r][c];
        if (ch == 'C') { *isInf = false; *ftime = t; return; }
        if (ch == '/') dir = slash_reflect[dir];
        else if (ch == '\\') dir = back_reflect[dir];

        if (vis[r][c][dir]) { *isInf = true; *ftime = -1; return; }
        vis[r][c][dir] = 1;
    }
}

int main(void) {
    if (scanf("%d %d", &N, &M) != 2) return 0;
    for (int i = 0; i < N; ++i) scanf("%500s", g[i]);
    scanf("%d %d", &PR, &PC);
    PR--; PC--;

    bool anyInf = false;
    long long bestTime = -1;
    int bestDir = 0;

    for (int d = 0; d < 4; ++d) {
        bool inf; long long t;
        simulate(d, &inf, &t);
        if (inf) {
            if (!anyInf) { anyInf = true; bestDir = d; }
        } else if (!anyInf) {
            if (t > bestTime) { bestTime = t; bestDir = d; }
        }
    }

    printf("%c\n", dirChar[bestDir]);
    if (anyInf) puts("Voyager");
    else printf("%lld\n", bestTime);
    return 0;
}