#include <stdio.h>
#include <stdlib.h>

int main(void) {
    long long n;
    int r, c;
    if (scanf("%lld %d %d", &n, &r, &c) != 3) return 0;

    long long t = (n + 1) / 2;
    int f = (int)(t < r ? (t < c ? t : c) : (r < c ? r : c));

    char **g = (char**)malloc(sizeof(char*) * r);
    for (int i = 0; i < r; ++i) {
        g[i] = (char*)malloc(sizeof(char) * (c + 1));
        for (int j = 0; j < c; ++j) g[i][j] = '.';
        g[i][c] = '\0';
    }

    long long placed = 0;
    for (int j = 0; j < f; ++j) { g[0][j] = '#'; ++placed; }
    for (int i = 0; i < f; ++i) {
        if (g[i][0] != '#') { g[i][0] = '#'; ++placed; }
    }

    for (int i = 0; i < r && placed < n; ++i) {
        for (int j = 0; j < c && placed < n; ++j) {
            if (g[i][j] == '.') { g[i][j] = '#'; ++placed; }
        }
    }

    printf("%d\n", f);
    for (int i = 0; i < r; ++i) {
        printf("%s\n", g[i]);
        free(g[i]);
    }

    free(g);
    return 0;
}