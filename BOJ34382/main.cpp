#include <bits/stdc++.h>
using namespace std;

static long long parse_scaled_1e5(const string& s) {
    int i = 0;
    int sign = 1;
    if (i < (int)s.size() && (s[i] == '-' || s[i] == '+')) {
        if (s[i] == '-') sign = -1;
        i++;
    }

    long long ip = 0;
    while (i < (int)s.size() && s[i] != '.') {
        if (isdigit((unsigned char)s[i])) ip = ip * 10 + (s[i] - '0');
        i++;
    }

    long long frac = 0;
    int len = 0;
    if (i < (int)s.size() && s[i] == '.') {
        i++;
        while (i < (int)s.size() && len < 5) {
            if (isdigit((unsigned char)s[i])) {
                frac = frac * 10 + (s[i] - '0');
                len++;
            }
            i++;
        }
    }

    while (len < 5) { frac *= 10; len++; }
    long long scaled = ip * 100000LL + frac;
    return sign * scaled;
}

static __int128 i128_abs(__int128 x) { return x < 0 ? -x : x; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    if (!cin) return 0;

    if (N == 0) {
        cout << 0 << "\n";
        return 0;
    }

    vector<long long> a(N);
    long long sum = 0;
    for (int i = 0; i < N; i++) {
        string t;
        cin >> t;
        a[i] = parse_scaled_1e5(t);
        sum += a[i];
    }

    const long long TH = 1000000LL;
    int ans = 0;
    for (int i = 0; i < N; i++) {
        __int128 dist = i128_abs((__int128)a[i] * N - sum);
        __int128 bound = (__int128)TH * N;
        if (dist > bound) ans++;
    }

    cout << ans << "\n";
    return 0;
}