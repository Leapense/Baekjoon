#include <stdio.h>
#include <stdint.h>

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) return 0;

    char grid[55][55];
    for (int i = 0; i < N; ++i) {
        scanf("%s", grid[i]);
    }

    typedef unsigned long long u64;
    __uint128_t prod128;

    u64 Hr[55], Hc[55];
    for (int r = 0; r < N; ++r) {
        u64 A = (u64)(r + 1) * (u64)(2 * N - r);
        u64 B = (u64)(r + N + 1) * (u64)(N - r);
        Hr[r] = A + B;
    }

    for (int c = 0; c < M; ++c) {
        u64 A = (u64)(c + 1) * (u64)(2 * M - c);
        u64 B = (u64)(c + M + 1) * (u64)(M - c);
        Hc[c] = A + B;
    }

    u64 ans[26] = {0};

    for (int r = 0; r < N; ++r) {
        for (int c = 0; c < M; ++c) {
            int k = grid[r][c] - 'A';
            prod128 = (__uint128_t)Hr[r] * (__uint128_t)Hc[c];
            ans[k] += (u64)prod128;
        }
    }

    for (int i = 0; i < 26; ++i) {
        printf("%llu\n", (unsigned long long)ans[i]);
    }

    return 0;
}