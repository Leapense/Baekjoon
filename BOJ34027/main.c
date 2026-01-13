#include <stdio.h>
#include <math.h>

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;

    while (T--) {
        long long N;
        scanf("%lld", &N);

        long long r = (long long)sqrtl((long double)N);

        while (r * r < N) r++;
        while (r * r > N) r--;

        puts((r * r == N) ? "1" : "0");
    }

    return 0;
}
