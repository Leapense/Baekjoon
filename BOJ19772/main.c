#include <stdio.h>

int max_i(int x, int y) { return x > y ? x : y; }
int min_i(int x, int y) { return x < y ? x : y; }

int main(void) {
    int a, b, c, d, e;
    scanf("%d %d %d %d %d", &a, &b, &c, &d, &e);

    int U = min_i(b, c - 1);
    int hi = c - d;
    int lo = c - e;

    int S_above     = U - hi;
    int S_below     = lo - a;
    int S_mid_max   = max_i(0, e - d - 1);
    int S_high_max  = max_i(0, b - c + 1);

    printf("%d %d\n", S_above + S_below, S_above + S_below + S_mid_max + S_high_max);

    return 0;
}