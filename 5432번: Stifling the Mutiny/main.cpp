#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>

#if __has_include(<print>)
    #include <print>
    using std::print;
    using std::println;
#else
    template<typename... Args>
    void println(const char* fmt, Args&&... args) {
        printf(fmt, args...);
        printf("\n");
    }
#endif

constexpr int MAX_N = 16;
constexpr int MAX_K = 42;

int memo[MAX_N][MAX_K][MAX_K][MAX_K];
int N, K;

int solve(int idx, int p1, int p2, int used) {
    if (used > K) return -1000;
    if (idx == N) {
        int last_L = std::max(1, p2 + p1);
        if (used + last_L <= K) return 0;
        else return -1000;
    }

    if (memo[idx][p1][p2][used] != -1) {
        return memo[idx][p1][p2][used];
    }

    int max_disloyal = -1000;

    for (int curr_d = 0; curr_d <= K; ++curr_d) {
        int cost_add = curr_d;
        if (idx > 0) {
            int needed_L = std::max(1, p2 + p1 + curr_d);
            cost_add += needed_L;
        }
        if (used + cost_add > K) break;
        int remaining_res = solve(idx + 1, curr_d, p1, used + cost_add);
        if (remaining_res >= 0) {
            max_disloyal = std::max(max_disloyal, curr_d + remaining_res);
        }
    }

    return memo[idx][p1][p2][used] = max_disloyal;
}

void solve_test_case() {
    if (!(std::cin >> N >> K)) return;
    std::memset(memo, -1, sizeof(memo));
    int result = solve(0, 0, 0, 0);
    println("{}", std::max(0, result));
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    int t;
    if (std::cin >> t) {
        while (t--) {
            solve_test_case();
        }
    }

    return 0;
}