#include <stdio.h>

static long long toH(long long x) {
    if (x > 0) return x;
    return x + 1;
}

static long long toFloor(long long h) {
    if (h >= 1) return h;
    return h - 1;
}

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    long long curH = toH(1);

    for (int i = 0; i < N; i++) {
        long long A, B;
        scanf("%lld %lld", &A, &B);
        curH += toH(B) - toH(A);
    }

    printf("%lld\n", toFloor(curH));
    return 0;
}