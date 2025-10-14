#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

static inline bool inb(int r, int c) {
    return 0 <= r && r < 8 && 0 <= c && c < 8;
}

static inline void mark(bool attacked[8][8], int rr, int cc) {
    if (inb(rr, cc)) attacked[rr][cc] = true;
}

static inline void slide (int sr, int sc , const int dirs[][2], int ndir, bool attacked[8][8], bool occupied[8][8]) {
    for (int d = 0; d < ndir; ++d) {
        int dr = dirs[d][0], dc = dirs[d][1];
        int rr = sr + dr, cc = sc + dc;
        while (inb(rr, cc)) {
            attacked[rr][cc] = true;
            if (occupied[rr][cc]) break;
            rr += dr;
            cc += dc;
        }
    }
}

int main(void) {
    char line[256];

    while (fgets(line, sizeof line, stdin)) {
        line[strcspn(line, "\r\n")] = '\0';
        if (line[0] == '\0') continue;

        char board[8][8];
        bool occupied[8][8] = {0};
        bool attacked[8][8]  = {0};

        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                board[i][j] = '.';

        int r = 0, c = 0;
        for (const char *p = line; *p; ++p) {
            unsigned char uch = (unsigned char)*p;
            if (uch == '/') { r++; c = 0; continue; }
            if (isdigit(uch)) {
                int k = (int)(uch - '0');
                for (int i = 0; i < k; ++i) board[r][c++] = '.';
            } else {
                board[r][c++] = (char)uch;
            }
        }

        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                occupied[i][j] = (board[i][j] != '.');

        const int K8[8][2] = { {-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1} };
        const int N8[8][2] = { {-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1} };
        const int B4[4][2] = { {-1,-1},{-1,1},{1,-1},{1,1} };
        const int R4[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char pch = board[i][j];
                if (pch == '.') continue;

                if (pch == 'P') {
                    mark(attacked, i-1, j-1); mark(attacked, i-1, j+1);
                } else if (pch == 'p') {
                    mark(attacked, i+1, j-1); mark(attacked, i+1, j+1);
                } else if (pch == 'N' || pch == 'n') {
                    for (int k = 0; k < 8; ++k) mark(attacked, i + N8[k][0], j + N8[k][1]);
                } else if (pch == 'K' || pch == 'k') {
                    for (int k = 0; k < 8; ++k) mark(attacked, i + K8[k][0], j + K8[k][1]);
                } else if (pch == 'B' || pch == 'b') {
                    slide(i, j, B4, 4, attacked, occupied);
                } else if (pch == 'R' || pch == 'r') {
                    slide(i, j, R4, 4, attacked, occupied);
                } else if (pch == 'Q' || pch == 'q') {
                    slide(i, j, B4, 4, attacked, occupied);
                    slide(i, j, R4, 4, attacked, occupied);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                if (!occupied[i][j] && !attacked[i][j]) ++ans;

        printf("%d\n", ans);
    }
    return 0;
}