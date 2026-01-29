#include <stdio.h>

int main(void) {
    int N, D, K;
    if (scanf("%d %d %d", &N, &D, &K) != 3) return 0;

    int L = 1e9;
    for (int i = 0; i < N; i++) {
        int s;
        scanf("%d", &s);
        int li = K / s;
        if (li < L) L = li;
    }

    int ans = (D - 1) / L;
    printf("%d\n", ans);

    return 0;
}