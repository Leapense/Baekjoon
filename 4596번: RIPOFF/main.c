#include <stdio.h>
#include <string.h>
#include <limits.h>

#define MAX_N 200
#define MAX_T 201
#define INF (LLONG_MIN / 4)

long long max_ll(long long a, long long b) {
    return (a > b) ? a : b;
}

int main(void) {
    int N;
    while (1) {
        if (scanf("%d", &N) != 1) return 0;
        if (N == 0) break;

        int S, T;
        scanf("%d %d", &S, &T);

        long long board[MAX_N + 1];
        for (int i = 1; i <= N; i++) {
            scanf("%lld", &board[i]);
        }

        static long long dp[MAX_T + 1][MAX_N + 2];
        for (int t = 0; t <= T; t++) {
            for (int pos = 0; pos <= N + 1; pos++) {
                dp[t][pos] = INF;
            }
        }
        dp[0][0] = 0;

        for (int t = 0; t < T; t++) {
            for (int pos = 0; pos <= N + 1; pos++) {
                if (dp[t][pos] == INF) continue;

                for (int step = 1; step <= S; step++) {
                    int newPos = pos + step;
                    if (newPos > N + 1) continue;

                    long long gain = 0;
                    if (1 <= newPos && newPos <= N) {
                        gain = board[newPos];
                    }
                    long long candidate = dp[t][pos] + gain;
                    if (candidate > dp[t + 1][newPos]) {
                        dp[t + 1][newPos] = candidate;
                    }
                }
            }
        }
        long long answer = INF;
        for (int t = 1; t <= T; t++) {
            if (dp[t][N + 1] > answer) {
                answer = dp[t][N + 1];
            }
        }

        printf("%lld\n", answer);
    }

    return 0;
}