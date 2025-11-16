#include <stdio.h>
#include <stdint.h>

#define MAXDIV 50000

static long long divs[MAXDIV];
static long long tmp[MAXDIV];

int main(void) {
    long long A;

    while (1) {
        if (scanf("%lld", &A) != 1) return 0;
        if (A == 0) break;

        long long n = A;
        long long prime[20];
        int exp[20];
        int pcnt = 0;

        for (long long p = 2; p * p <= n; ++p) {
            if (n % p == 0) {
                int e = 0;
                while (n % p == 0) {
                    n /= p;
                    ++e;
                }
                prime[pcnt] = p;
                exp[pcnt] = e;
                ++pcnt;
            }
        }
        if (n > 1) {
            prime[pcnt] = n;
            exp[pcnt] = 1;
            ++pcnt;
        }

        int div_cnt = 1;
        divs[0] = 1;

        for (int i = 0; i < pcnt; ++i) {
            long long p = prime[i];
            int e2 = exp[i] * 2;
            int new_cnt = 0;

            for (int j = 0; j < div_cnt; ++j) {
                long long val = divs[j];
                for (int k = 0; k <= e2; ++k) {
                    if (new_cnt >= MAXDIV) return 0;
                    tmp[new_cnt++] = val;
                    val *= p;
                }
            }

            for (int j = 0; j < new_cnt; ++j) {
                divs[j] = tmp[j];
            }
            div_cnt = new_cnt;
        }

        long long A2 = A * A;
        long long ans = 0;

        for (int i = 0; i < div_cnt; ++i) {
            long long v = divs[i];
            long long u = A2 / v;

            if (v <= u) continue;
            if ((u + v) & 1LL) continue;

            long long B = (v - u) / 2;
            if (B > A) {
                ++ans;
            }
        }

        printf("%lld\n", ans);
    }

    return 0;
}