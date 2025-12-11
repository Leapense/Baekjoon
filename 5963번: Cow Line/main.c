#include <stdio.h>
#include <stdbool.h>

int main(void) {
    int N, K;
    if (scanf("%d %d", &N, &K) != 2) {
        return 0;
    }

    unsigned long long fact[21];
    fact[0] = 1;
    for (int i = 1; i <= N; ++i) {
        fact[i] = fact[i - 1] * (unsigned long long)i;
    }

    for (int qi = 0; qi < K; ++qi) {
        char cmd;
        if (scanf(" %c", &cmd) != 1) {
            return 0;
        }

        if (cmd == 'P') {
            unsigned long long A;
            if (scanf("%llu", &A) != 1) return 0;
            int remaining[21];
            int remSize = N;
            for (int i = 0; i < N; ++i) {
                remaining[i] = i + 1;
            }

            unsigned long long x = A - 1;
            int result[21];

            for (int pos = 1; pos <= N; ++pos) {
                unsigned long long block = fact[N - pos];
                unsigned long long idx = x / block;
                x = x % block;

                int chosen = remaining[idx];
                result[pos] = chosen;

                for (int j = (int)idx; j < remSize - 1; ++j) {
                    remaining[j] = remaining[j + 1];
                }
                --remSize;
            }

            for (int i = 1; i <= N; ++i) {
                if (i > 1) printf(" ");
                printf("%d", result[i]);
            }

            printf("\n");
        } else if (cmd == 'Q') {
            int perm[21];
            for (int i = 1; i <= N; ++i) {
                if (scanf("%d", &perm[i]) != 1) return 0;
            }
            bool used[21] = {false};
            unsigned long long rank = 1;

            for (int i = 1; i <= N; ++i) {
                int p = perm[i];
                int cnt = 0;
                for (int x = 1; x < p; ++x) {
                    if (!used[x]) ++cnt;
                }
                rank += (unsigned long long)cnt * fact[N - i];
                used[p] = true;
            }

            printf("%llu\n", rank);
        }
    }

    return 0;
}