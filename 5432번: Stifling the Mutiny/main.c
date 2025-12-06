#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_N 16
#define MAX_K 42

static int memo[MAX_N][MAX_K][MAX_K][MAX_K];
static int N, K;

#define MAX(a, b) ((a) > (b) ? (a) : (b))

int solve(int idx, int p1, int p2, int used) {
    if (used > K) return -1000;
    if (idx == N) {
        int last_L = MAX(1, p2 + p1);
        if (used + last_L <= K) {
            return 0;
        }
        return -1000;
    }

    if (memo[idx][p1][p2][used] != -1) {
        return memo[idx][p1][p2][used];
    }

    int max_d = -1000;
    for (int curr_d = 0; curr_d <= K - used; ++curr_d) {
        int current_cost_increase = curr_d;
        if (idx > 0) {
            int required_L = MAX(1, p2 + p1 + curr_d);
            current_cost_increase += required_L;
        }

        int res = solve(idx + 1, curr_d, p1, used + current_cost_increase);
        if (res >= 0) {
            if (curr_d + res > max_d) {
                max_d = curr_d + res;
            }
        }
    }

    return memo[idx][p1][p2][used] = max_d;
}

void run_test_case() {
    if (scanf("%d %d", &N, &K) != 2) return;
    memset(memo, -1, sizeof(memo));
    int result = solve(0, 0, 0, 0);
    if (result < 0) result = 0;
    printf("%d\n", result);
}

int main() {
    int t;
    if (scanf("%d", &t) == 1) {
        while (t--) {
            run_test_case();
        }
    }

    return 0;
}