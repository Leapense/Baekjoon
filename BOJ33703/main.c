#include <stdio.h>

int main(void) {
    long long N;
    scanf("%lld", &N);

    printf("%lld", N * (N + 1) / 2);
    return 0;
}