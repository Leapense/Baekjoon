#include <stdio.h>
#include <string.h>

#define MAXA 1000000
#define MAXN 100

static unsigned char isPrime[MAXA + 1];

void init_primes(void) {
    for (int i = 0; i <= MAXA; ++i) isPrime[i] = 1;
    isPrime[0] = isPrime[1] = 0;
    for (int i = 2; i * i <= MAXA; ++i) {
        if (isPrime[i]) {
            for (int j = i * i; j <= MAXA; j += i) {
                isPrime[j] = 0;
            }
        }
    }
}

int digit_sum(int x) {
    int s = 0;
    while (x > 0) {
        s += x % 10;
        x /= 10;
    }
    return s;
}

int digit_prod(int x) {
    int p = 1;
    while (x > 0) {
        int d = x % 10;
        p *= d;
        x /= 10;
    }

    return p;
}

int is_square(int x) {
    for (int i = 1; (long long)i * i <= x; ++i) {
        if ((long long)i * i == x) return 1;
    }
    return 0;
}

int is_cube(int x) {
    for (int i = 1; (long long)i * i * i <= x; ++i) {
        if ((long long)i * i * i == x) return 1;
    }

    return 0;
}

int is_fourth(int x) {
    for (int i = 1; (long long)i * i * i * i <= x; ++i) {
        if ((long long)i * i * i * i == x) return 1;
    }

    return 0;
}

void sort_ints(int *a, int n) {
    if (n <= 1) return;
    int pivot = a[n / 2];
    int i = 0, j = n - 1;
    while (i <= j) {
        while (a[i] < pivot) ++i;
        while (a[j] > pivot) --j;
        if (i <= j) {
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            ++i; --j;
        }
    }

    if (j > 0) sort_ints(a, j + 1);
    if (i < n) sort_ints(a + i, n - i);
}

int main(void) {
    init_primes();
    int T;
    if (scanf("%d", &T) != 1) return 0;
    for (int tc = 1; tc <= T; ++tc) {
        int N;
        if (scanf("%d", &N) != 1) break;

        int a[MAXN];
        for (int i = 0; i < N; ++i) {
            scanf("%d", &a[i]);
        }

        sort_ints(a, N);
        
        int sum[MAXN], prod[MAXN];
        for (int i = 0; i < N; ++i) {
            sum[i] = digit_sum(a[i]);
            prod[i] = digit_prod(a[i]);
        }

        int cnt[MAXN];
        for (int i = 0; i < N; ++i) {
            int x = a[i];
            int c = 0;

            if (x >= 2 && isPrime[x]) ++c;
            if (is_square(x)) ++c;
            if (is_cube(x)) ++c;
            if (is_fourth(x)) ++c;

            int s = sum[i];
            if (s != 0 && x % s == 0) ++c;

            int p = prod[i];
            if (p != 0 && x % p == 0) ++c;

            int f7 = 0, f8 = 0, f9 = 0, f10 = 0, f11 = 0, f12 = 0, f13 = 0;

            for (int j = 0; j < N; ++j) {
                if (j == i) continue;
                int y = a[j];

                if (!f7 && y % x == 0) f7 = 1;
                if (!f8 && x % y == 0) f8 = 1;

                if (!f9) {
                    long long sq = (long long)y * y;
                    if (sq == x) f9 = 1;
                }

                if (!f10) {
                    long long cube = (long long)y * y * y;
                    if (cube == x) f10 = 1;
                }

                if (!f11) {
                    long long sq = (long long)y * y;
                    if (sq <= x) {
                        long long fourth = sq * sq;
                        if (fourth == x) f11 = 1;
                    }
                }

                if (!f12) {
                    int sy = sum[j];
                    if (sy != 0 && x % sy == 0) f12 = 1;
                }

                if (!f13) {
                    int py = prod[j];
                    if (py != 0 && x % py == 0) f13 = 1;
                }

                if (f7 && f8 && f9 && f10 && f11 && f12 && f13) break;
            }

            c += f7 + f8 + f9 + f10 + f11 + f12 + f13;
            cnt[i] = c;
        }

        int maxCnt = 0;
        for (int i = 0; i < N; ++i) {
            if (cnt[i] > maxCnt) maxCnt = cnt[i];
        }

        printf("DATA SET #%d\n", tc);
        for (int i = 0; i < N; ++i) {
            if (cnt[i] == maxCnt) printf("%d\n", a[i]);
        }
    }

    return 0;
}