#include <stdio.h>
#include <stdbool.h>

static bool win(char b[3][4], char p) {
    for (int r = 0; r < 3; ++r) {
        if (b[r][0] == p && b[r][1] == p && b[r][2] == p) return true;
    }

    for (int c = 0; c < 3; ++c) {
        if (b[0][c] == p && b[1][c] == p && b[2][c] == p) return true;
    }

    if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
    if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
    return false;
}

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;
    for (int tc = 0; tc < N; ++tc) {
        char b[3][4];
        for (int i = 0; i < 3; ++i) {
            if (scanf("%3s", b[i]) != 1) return 0;
        }

        int x = 0, o = 0;
        for (int r = 0; r < 3; ++r) {
            for (int c = 0; c < 3; ++c) {
                if (b[r][c] == 'X') ++x;
                else if (b[r][c] == 'O') ++o;
            }
        }

        bool cnt_ok = (x == o) || (x == o + 1);
        bool wx = win(b, 'X');
        bool wo = win(b, 'O');

        bool ok = false;
        if (!cnt_ok) {
            ok = false;
        } else if (wx && wo) {
            ok = false;
        } else if (wx) {
            ok = (x == o + 1);
        } else if (wo) {
            ok = (x == o);
        } else {
            ok = true;
        }

        puts(ok ? "yes" : "no");
    }

    return 0;
}