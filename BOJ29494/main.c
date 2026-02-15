#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <errno.h>
#include <limits.h>

static inline int opp(int d) { return (d + 2) & 3; }

static void rotate4(const int oldv[4], int k, int newv[4]) {
    for (int i = 0; i < 4; i++) {
        int idx = (i - k) & 3;
        newv[i] = oldv[idx];
    }
}

bool safe_read_int(int *out) {
    char buf[64];
    if (scanf("%63s", buf) != 1) return false;

    char *endptr;
    errno = 0;
    long val = strtol(buf, &endptr, 10);

    if (errno == ERANGE || *endptr != '\0' || val > INT_MAX || val < INT_MIN) {
        return false;
    }

    *out = (int)val;
    return true;
}

int main(void) {
    int base[3][4];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
            if (!safe_read_int(&base[i][j])) {
                fprintf(stderr, "Invalid input detected.\n");
                return 1;
            }
        }
    }

    int rot[3][4][4];
    for (int s = 0; s < 3; s++) {
        for (int k = 0; k < 4; k++) {
            rotate4(base[s], k, rot[s][k]);
        }
    }

    const int pairs[6][2] = {
        {0,1},{1,2},{2,3},{3,0},
        {0,2},{1,3}
    };

    for (int r0 = 0; r0 < 4; r0++) {
        for (int r1 = 0; r1 < 4; r1++) {
            for (int r2 = 0; r2 < 4; r2++) {
                const int* cur[3] = {rot[0][r0], rot[1][r1], rot[2][r2]};
                for (int c = 0; c < 3; c++) {
                    int others[2], t = 0;
                    for (int i = 0; i < 3; i++) {
                        if (i != c) others[t++] = i;
                    }

                    for (int pi = 0; pi < 6; pi++) {
                        int e1 = pairs[pi][0], e2 = pairs[pi][1];
                        for (int sw = 0; sw < 2; sw++) {
                            int a = others[sw];
                            int b = others[1 - sw];

                            if (cur[c][e1] == cur[a][opp(e1)] && cur[c][e2] == cur[b][opp(e2)]) {
                                puts("Yes");
                                return 0;
                            }
                        }
                    }
                }
            }
        }
    }

    puts("No");
    return 0;
}