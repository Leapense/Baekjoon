#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long A;
    while (true) {
        if (!(cin >> A)) return 0;
        if (A == 0) break;

        long long n = A;
        vector<long long> primes;
        vector<int> exps;

        for (long long p = 2; p * p <= n; ++p) {
            if (n % p == 0) {
                int e = 0;
                while (n % p == 0) {
                    n /= p;
                    ++e;
                }

                primes.push_back(p);
                exps.push_back(e);
            }
        }

        if (n > 1) {
            primes.push_back(n);
            exps.push_back(1);
        }

        vector<long long> divs = {1};
        for (size_t i = 0; i < primes.size(); ++i) {
            long long p = primes[i];
            int e2 = exps[i] * 2;
            vector<long long> next;
            next.reserve(divs.size() * (e2 + 1));

            for (auto d : divs) {
                long long val = d;
                for (int k = 0; k <= e2; ++k) {
                    next.push_back(val);
                    val *= p;
                }
            }

            divs.swap(next);
        }

        long long A2 = A * A;
        long long ans = 0;
        for (auto v : divs) {
            long long u = A2 / v;
            if (v <= u) continue;
            if ((u + v) & 1LL) continue;

            long long B = (v - u) / 2;
            if (B > A) {
                ++ans;
            }
        }
        cout << ans << '\n';
    }

    return 0;
}