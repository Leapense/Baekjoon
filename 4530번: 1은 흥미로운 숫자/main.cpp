#include <bits/stdc++.h>
using namespace std;

constexpr int MAXA = 1'000'000;
constexpr int MAXN = 100;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    static vector<bool> isPrime(MAXA + 1, true);
    isPrime[0] = isPrime[1] = false;
    for (int i = 2; i * i <= MAXA; ++i) {
        if (isPrime[i]) {
            for (int j = i * i; j <= MAXA; j += i) {
                isPrime[j] = false;
            }
        }
    }

    auto digit_sum = [](int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }

        return s;
    };

    auto digit_prod = [](int x) {
        int p = 1;
        while (x > 0) {
            int d = x % 10;
            p *= d;
            x /= 10;
        }
        return p;
    };

    auto is_square = [](int x) {
        for (int i = 1; 1LL * i * i <= x; ++i) {
            if (1LL * i * i == x) return true;
        }

        return false;
    };

    auto is_cube = [](int x) {
        for (int i = 1; 1LL * i * i * i <= x; ++i) {
            if (1LL * i * i * i == x) return true;
        }
        return false;
    };

    auto is_fourth = [](int x) {
        for (int i = 1; 1LL * i * i * i * i <= x; ++i) {
            if (1LL * i * i * i * i == x) return true;
        }

        return false;
    };

    int T;
    if (!(cin >> T)) return 0;

    for (int tc = 1; tc <= T; ++tc) {
        int N;
        cin >> N;
        vector<int> a(N);
        for (int i = 0; i < N; ++i) cin >> a[i];

        sort(a.begin(), a.end());
        vector<int> sum(N), prod(N);
        for (int i = 0; i < N; ++i) {
            sum[i] = digit_sum(a[i]);
            prod[i] = digit_prod(a[i]);
        }

        vector<int> cnt(N, 0);
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

            bool f7 = false, f8 = false, f9 = false, f10 = false, f11 = false, f12 = false, f13 = false;

            for (int j = 0; j < N; ++j) {
                if (j == i) continue;
                int y = a[j];
                if (!f7 && y % x == 0) f7 = true;
                if (!f8 && x % y == 0) f8 = true;
                if (!f9) {
                    long long sq = 1LL * y * y;
                    if (sq == x) f9 = true;
                }
                if (!f10) {
                    long long cube = 1LL * y * y * y;
                    if (cube == x) f10 = true;       
                }
                if (!f11) {
                    long long sq = 1LL * y * y;
                    if (sq <= x) {
                        long long fourth = sq * sq;
                        if (fourth == x) f11 = true;
                    }
                }
                if (!f12) {
                    int sy = sum[j];
                    if (sy != 0 && x % sy == 0) f12 = true;
                }
                if (!f13) {
                    int py = prod[j];
                    if (py != 0 && x % py == 0) f13 = true;
                }

                if (f7 && f8 && f9 && f10 && f11 && f12 && f13) break;
            }

            c += (int)f7 + (int)f8 + (int)f9 + (int)f10 + (int)f11 + (int)f12 + (int)f13;
            cnt[i] = c;
        }

        int maxCnt = 0;
        for (int v : cnt) maxCnt = max(maxCnt, v);
        cout << "DATA SET #" << tc << "\n";
        for (int i = 0; i < N; ++i) {
            if (cnt[i] == maxCnt) {
                cout << a[i] << "\n";
            }
        }
    }

    return 0;
}