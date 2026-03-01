#include <stdio.h>

int main(void)
{
    long long N, M;
    if (scanf("%lld %lld", &N, &M) != 2) return 0;

    long long L = (N < M ? N : M) - 1;
    long long ans;

    if (N != M) {
        ans = L * L;
    } else {
        ans = (L - 1) * (L - 1) + 1;
    }

    printf("%lld\n", ans);
    return 0;
}