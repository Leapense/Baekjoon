#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_ROWS 60
#define MAX_COLS 60

static int is_base_row(const char *s, int *first, int *last) {
    int n = (int)strlen(s);
    *first = -1;
    *last = -1;

    for (int i = 0; i < n; i++) {
        if (s[i] == '#') { *first = i; break; }
    }

    if (*first == -1) return 0;

    for (int i = n - 1; i >= 0; i--) {
        if (s[i] == '#') { *last = i; break; }
    }

    int w = *last - *first + 1;
    if (w < 3) return 0;

    for (int i = *first; i <= *last; i++) {
        if (s[i] != '#') return 0;
    }

    return 1;
}

static char at_or_space(const char *s, int idx) {
    int n = (int)strlen(s);
    if (idx < 0 || idx >= n) return ' ';
    return s[idx];
}

int main(void) {
    char rows[MAX_ROWS][MAX_COLS];
    int R = 0;

    char buf[256];
    while (R < MAX_ROWS && fgets(buf, sizeof(buf), stdin)) {
        size_t len = strlen(buf);
        while (len > 0 && (buf[len - 1] == '\n' || buf[len - 1] == '\r')) {
            buf[--len] = '\0';
        }

        strncpy(rows[R], buf, sizeof(rows[R]) - 1);
        rows[R][sizeof(rows[R]) - 1] = '\0';
        R++;
    }

    int base_idx = -1, base_l = -1, base_r = -1;
    for (int i = 0; i < R; i++) {
        int l, r;
        if (is_base_row(rows[i], &l, &r)) {
            base_idx = i;
            base_l = l;
            base_r = r;
            break;
        }
    }

    if (base_idx == -1) {
        return 0;
    }

    int p = base_idx;
    printf("%d\n", p);

    int first_tine_row = base_idx + 1;

    int tine_cols[MAX_COLS], t = 0;
    if (first_tine_row < R) {
        for (int c = base_l; c <= base_r && c < MAX_COLS; c++) {
            if (at_or_space(rows[first_tine_row], c) == '#') {
                tine_cols[t++] = c;
            }
        }
    }

    int lens[MAX_COLS];
    for (int i = 0; i < t; i++) lens[i] = 0;

    for (int i = base_idx + 1; i < R; i++) {
        for (int j = 0; j < t; j++) {
            if (at_or_space(rows[i], tine_cols[j]) == '#') lens[j]++;
        }
    }

    for (int i = 0; i < t; i++) {
        if (i) putchar(' ');
        printf("%d", lens[i]);
    }
    if (t > 0) putchar('\n');
    return 0;
}