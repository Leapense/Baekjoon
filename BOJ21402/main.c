#include <stdio.h>

int main(void) {
    int n, k;
    if (scanf("%d %d", &n, &k) != 2) return 0;
    int open = 0;

    for (int i = 0; i < n; i++) {
        int a, b;
        scanf("%d %d", &a, &b);

        if (open <= a + b) open = b;
        else open = open - a;
    }

    printf("%d\n", k - open);
    return 0;
}