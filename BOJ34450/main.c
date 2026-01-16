#include <stdio.h>

int main(void) {
    int n, p;
    if (scanf("%d", &n) != 1) return 0;
    if (scanf("%d", &p) != 1) return 0;

    int total = 0;
    for (int k = 0; k < n; k++) total += (p + k);

    int given = 0, x;
    for (int i = 0; i < n - 1; i++) {
        scanf("%d", &x);
        given += x;
    }

    printf("%d\n", total - given);

    return 0;
}