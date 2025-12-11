#include <iostream>
#include <vector>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int N, K;
    if (!(std::cin >> N >> K)) return 0;

    std::vector<unsigned long long> fact(N + 1);
    fact[0] = 1;
    for (int i = 1; i <= N; ++i) {
        fact[i] = fact[i - 1] * (unsigned long long)i;
    }

    for (int qi = 0; qi < K; ++qi) {
        char cmd;
        std::cin >> cmd;

        if (cmd == 'P') {
            unsigned long long A;
            std::cin >> A;

            std::vector<int> remaining(N);
            for (int i = 0; i < N; ++i) {
                remaining[i] = i + 1;
            }

            unsigned long long x = A - 1;
            std::vector<int> result;
            result.reserve(N);

            for (int pos = 0; pos < N; ++pos) {
                unsigned long long block = fact[N - 1 - pos];
                unsigned long long idx = x / block;
                x = x % block;

                int chosen = remaining[(std::size_t)idx];
                result.push_back(chosen);
                remaining.erase(remaining.begin() + (std::ptrdiff_t)idx);
            }

            for (int i = 0; i < N; ++i) {
                if (i) std::cout << ' ';
                std::cout << result[i];
            }
            std::cout << '\n';

        } else if (cmd == 'Q') {
            std::vector<int> perm(N);
            for (int i = 0; i < N; ++i) {
                std::cin >> perm[i];
            }

            std::vector<bool> used(N + 1, false);
            unsigned long long rank = 1;

            for (int i = 0; i < N; ++i) {
                int p = perm[i];
                int cnt = 0;
                for (int x = 1; x < p; ++x) {
                    if (!used[x]) ++cnt;
                }
                rank += (unsigned long long)cnt * fact[N - 1 - i];
                used[p] = true;
            }

            std::cout << rank << '\n';
        }
    }

    return 0;
}