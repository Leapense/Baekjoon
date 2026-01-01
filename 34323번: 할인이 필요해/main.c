#include <stdio.h>
#include <stdint.h>

int main(void) {
    int64_t N, M, S;
    if (scanf("%lld %lld %lld", &N, &M, &S) != 3) return 0;
    int64_t percent_cost = ((M + 1) * S * (100 - N)) / 100;
    int64_t promo_cost = M * S;
    int64_t ans = (percent_cost < promo_cost) ? percent_cost : promo_cost;
    printf("%lld\n", ans);
    return 0;
}