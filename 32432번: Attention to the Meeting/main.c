#include <stdio.h>

int main(void) {
    int N, K;
    if (scanf("%d %d", &N, &K) != 2) return 0;

    int S = (K - (N - 1)) / N;
    printf("%d\n", S);

    return 0;
}