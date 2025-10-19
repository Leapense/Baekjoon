#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    const int SIEVE_MAX = 1'000'000;
    vector<char> isPrime(SIEVE_MAX + 1, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; 1LL * i * i <= SIEVE_MAX; ++i) {
        if (isPrime[i]) {
            for (int j = i * i; j <= SIEVE_MAX; j += i) {
                isPrime[j] = false;
            }
        }
    }

    auto digits = [](int x) {
        int d = 0;
        while (x) {
            ++d;
            x /= 10;
        }
        return d;
    };
    int tenPow[7] = {1, 10, 100, 1000, 10000, 100000, 1000000};
    vector<int> shift(N + 1);
    for (int b = 1; b <= N; ++b) shift[b] = tenPow[digits(b)];

    vector<int> dp((N + 1) * (N + 1), 0);
    auto idx = [N](int a, int b) { return a * (N + 1) + b; };

    for (int a = 1; a <= N; ++a) {
        for (int b = 1; b <= N; ++b) {
            if (a == 1 && b == 1) continue;
            int concat = a * shift[b] + b;
            int v = ((a != 1 || b != 1) && isPrime[concat]) ? 1 : 0;

            if (a == 1) dp[idx(a, b)] = dp[idx(a, b - 1)] + v;
            else if (b == 1) dp[idx(a, b)] = dp[idx(a - 1, b)] + v;
            else dp[idx(a, b)] = max(dp[idx(a - 1, b)], dp[idx(a, b - 1)]) + v;
        }
    }

    cout << dp[idx(N, N)] << '\n';
    return 0;
}