#include <stdio.h>
#include <stdbool.h>

static const int DIG_MASK[10] = {
    119,
    36,
    93,
    109,
    46,
    107,
    123,
    37,
    127,
    47
};

int main(void) {
    char state[6][7];
    bool possible[6][10] = {0};

    for (int i = 0; i < 6; i++) {
        for (int s = 0; s < 7; s++) {
            char tok[4];
            if (scanf("%3s", tok) != 1) return 0;
            state[i][s] = tok[0];
        }
    }

    for (int pos = 0; pos < 6; pos++) {
        for (int d = 0; d <= 9; d++) {
            bool ok = true;
            int mask = DIG_MASK[d];
            for (int s = 0; s < 7; s++) {
                int on = (mask >> s) & 1;
                if (state[pos][s] == '1' && !on) { ok = false; break; }
                if (state[pos][s] == '0' && on) { ok = false; break; }
            }
            possible[pos][d] = ok;
        }
    }

    for (int h = 0; h <= 23; h++) {
        int h1 = h / 10, h2 = h % 10;
        if (!possible[0][h1] || !possible[1][h2]) continue;

        for (int m = 0; m <= 59; m++) {
            int m1 = m / 10, m2 = m % 10;
            if (!possible[2][m1] || !possible[3][m2]) continue;

            for (int s = 0; s <= 59; s++) {
                int s1 = s / 10, s2 = s % 10;
                if (!possible[4][s1] || !possible[5][s2]) continue;
                printf("%02d:%02d:%02d\n", h, m, s);
            }
        }
    }

    return 0;
}