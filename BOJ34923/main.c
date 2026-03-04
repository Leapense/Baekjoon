#include <stdio.h>

static int mod_pos(int a, int m) {
    int r = a % m;
    return (r < 0) ? r + m : r;
}

int main(void) {
    int h1, m1, h2, m2;
    if (scanf("%d:%d", &h1, &m1) != 2) return 0;
    if (scanf("%d:%d", &h2, &m2) != 2) return 0;

    h1 %= 12;
    h2 %= 12;

    int t1 = 60 * h1 + m1;
    int t2 = 60 * h2 + m2;

    int d = mod_pos(t2 - t1, 720);
    int min_minutes = d < (720 - d) ? d : (720 - d);
    int degrees = min_minutes * 6;
    printf("%d", degrees);

    return 0;
}