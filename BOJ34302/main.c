#include <stdio.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int cur = 0, ans = 0;
    for (int i = 0; i < N; i++) {
        int S, T;
        scanf("%d %d", &S, &T);

        if (S > T) cur++;
        else cur = 0;

        if (cur > ans) ans = cur;
    }

    printf("%d\n", ans);

    return 0;
}