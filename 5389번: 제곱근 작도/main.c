#include <stdio.h>

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;

    while (T--) {
        long long N;
        scanf("%lld", &N);

        int found = 0;
        long long bestA = 0, bestR = 0;

        for (long long i = 1; i * i <= N; ++i) {
            if (N % i != 0) continue;
            long long d1 = i;
            long long d2 = N / i;

            if ((d1 + d2) % 2 != 0) continue;
            long long a = (d2 - d1) / 2;
            long long r = (d1 + d2) / 2;

            if (!found || a < bestA || (a == bestA && r < bestR)) {
                found = 1;
                bestA = a;
                bestR = r;
            }
        }

        if (!found) {
            puts("IMPOSSIBLE");
        } else {
            printf("%lld %lld\n", bestA, bestR);
        }
    }

    return 0;
}