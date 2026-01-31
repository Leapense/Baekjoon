#include <stdio.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int denom[5] = {1, 5, 15, 30, 150};
    int cnt[5] = {0, 0, 0, 0, 0};

    for (int i = 4; i >= 0; --i) {
        cnt[i] = N / denom[i];
        N %= denom[i];
    }

    printf("%d %d %d %d %d\n", cnt[0], cnt[1], cnt[2], cnt[3], cnt[4]);
    return 0;
}