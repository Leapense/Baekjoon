#include <stdio.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int cur = 0, best = 0;
    for (int i = 0; i < N; i++) {
        int a, b;
        scanf("%d %d", &a, &b);
        if (a == 0 && b == 0) {
            cur++;
            if (cur > best) best = cur;
        } else {
            cur = 0;
        }
    }

    printf("%d\n", 2 * best);
    return 0;
}