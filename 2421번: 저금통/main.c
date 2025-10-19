#include <stdio.h>
#include <stdbool.h>

#define MAXN 999
#define SIEVE_MAX 1000000

static bool isPrime[SIEVE_MAX + 1];
static int dp[MAXN + 1][MAXN + 1];
static int tenPow[7] = {1, 10, 100, 1000, 10000, 100000, 1000000};
static int shiftPow[MAXN + 1];

static inline int digits(int x) {
    int d = 0;
    while (x) {
        d++;
        x /= 10;
    }
    return d;
}

static void build_sieve(void) {
    for (int i = 0; i <= SIEVE_MAX; ++i) isPrime[i] = true;
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i * (long long)i <= SIEVE_MAX; ++i) {
        if (isPrime[i]) {
            for (int j = i * i; j <= SIEVE_MAX; j += i) {
                isPrime[j] = false;
            }
        }
    }
}

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    build_sieve();
    for (int b = 1; b <= N; ++b) {
        int d = digits(b);
        shiftPow[b] = tenPow[d];
    }

    dp[1][1] = 0;
    for (int a = 1; a <= N; ++a) {
        for (int b = 1; b <= N; ++b) {
            if (a == 1 && b == 1) continue;
            int concat = a * shiftPow[b] + b;
            int v = ((a != 1 || b != 1) && isPrime[concat]) ? 1 : 0;
            if (a == 1) dp[a][b] = dp[a][b - 1] + v;
            else if (b == 1) dp[a][b] = dp[a - 1][b] + v;
            else {
                int bestPrev = dp[a - 1][b] > dp[a][b - 1] ? dp[a - 1][b] : dp[a][b - 1];
                dp[a][b] = bestPrev + v;
            }
        }
    }

    printf("%d\n", dp[N][N]);
    return 0;
}