#include <iostream>
#include <vector>
#include <limits>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    while (true) {
        int N;
        if (!(cin >> N)) return 0;
        if (N == 0) break;

        int S, T;
        cin >> S >> T;

        vector<long long> board(N + 1);
        for (int i = 1; i <= N; ++i) {
            cin >> board[i];
        }

        const long long INF = numeric_limits<long long>::min() / 4;
        vector<vector<long long>> dp(T + 1, vector<long long>(N + 2, INF));
        dp[0][0] = 0;

        for (int t = 0; t < T; ++t) {
            for (int pos = 0; pos <= N + 1; ++pos) {
                if (dp[t][pos] == INF) continue;
                for (int step = 1; step <= S; ++step) {
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
        for (int t = 1; t <= T; ++t) {
            if (dp[t][N + 1] > answer) {
                answer = dp[t][N + 1];
            }
        }

        cout << answer << "\n";
    }

    return 0;
}