#include <stdio.h>

static int win(char b[3][3], char p) {
    for (int r = 0; r < 3; r++) {
        if (b[r][0] == p && b[r][1] == p && b[r][2] == p) return 1;
    }
    for (int c = 0; c < 3; c++) {
        if (b[0][c] == p && b[1][c] == p && b[2][c] == p) return 1;
    }
    if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return 1;
    if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return 1;
    return 0;
}

int main(void) {
    char p;
    if (scanf(" %c", &p) != 1) return 0;

    char s[4];
    char b[3][3];
    for (int r = 0; r < 3; r++) {
        if (scanf("%3s", s) != 1) return 0;
        for (int c = 0; c < 3; c++) b[r][c] = s[c];
    }

    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            if (b[r][c] != 'E') continue;
            char backup = b[r][c];
            b[r][c] = p;
            if (win(b, p)) {
                printf("%d %d\n", r + 1, c + 1);
                return 0;
            }

            b[r][c] = backup;
        }
    }

    return 0;
}