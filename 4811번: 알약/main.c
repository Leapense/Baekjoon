#include <stdio.h>

static unsigned long long dp[31][31];
unsigned long long solve(int w, int h) {
    if (dp[w][h] != 0ULL) {
        return dp[w][h];
    }
    if (w == 0) {
        return dp[w][h] = 1ULL;
    }
    unsigned long long res = 0ULL;

    if (h > 0) {
        res += solve(w, h - 1);
    }

    res += solve(w - 1, h + 1);
    dp[w][h] = res;
    return res;
}

int main(void) {
    int N;
    while (1) {
        if (scanf("%d", &N) != 1) {
            return 0;
        }
        if (N == 0) {
            break;
        }
        unsigned long long answer = solve(N, 0);
        printf("%llu\n", answer);
    }

    return 0;
}