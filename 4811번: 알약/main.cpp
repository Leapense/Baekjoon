#include <iostream>
#include <array>

using namespace std;
static array<array<unsigned long long, 31>, 31> dp{};

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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N;
    while (true) {
        if (!(cin >> N)) {
            return 0;
        }
        if (N == 0) {
            break;
        }

        unsigned long long answer = solve(N, 0);
        cout << answer << '\n';
    }

    return 0;
}