#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) {
        return 0;
    }

    while (T--) {
        int N;
        scanf("%d", &N);

        long long *speed = (long long *)malloc((size_t)N * sizeof(long long));
        if (!speed) {
            return 1;
        }

        for (int i = 0; i < N; ++i) {
            scanf("%lld", &speed[i]);
        }

        long long blocked = 0;
        long long ans = 0;

        for (int i = 0; i < N; ++i) {
            char c;
            scanf(" %c", &c);

            if (c == 'A') {
                blocked += speed[i];
            } else {
                if (blocked >= speed[i]) {
                    blocked -= speed[i];
                } else {
                    ans += speed[i] - blocked;
                    blocked = 0;
                }
            }
        }

        printf("%lld\n", ans);
        free(speed);
    }

    return 0;
}