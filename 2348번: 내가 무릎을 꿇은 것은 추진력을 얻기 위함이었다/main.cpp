#include <bits/stdc++.h>
using namespace std;

static inline bool parse_upto9(const string& s, int pos, int len, int& out) {
    if (len <= 0 || pos < 0 || pos + len > (int)s.size()) return false;
    if (s[pos] == '0' && len > 1) return false;

    long long v = 0;
    for (int i = 0; i < len; ++i) {
        char c = s[pos + i];
        if (c < '0' || c > '9') return false;
        v = v * 10 + (c - '0');
        if (v >= 1000000000LL) return false;
    }

    if (v == 0) return false;
    out = (int)v;
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    string S;
    if (!(cin >> S)) return 0;

    const int L = (int)S.size();
    int bestF = INT_MAX;

    auto try_pair = [&](int l1, int l2) {
        int a1, a2;
        if (!parse_upto9(S, 0, l1, a1)) return;
        if (!parse_upto9(S, l1, l2, a2)) return;
        if (a2 <= a1) return;
        int d = a2 - a1;
        int pos = l1 + l2;
        int t = a2;

        while (true) {
            int remLen = L - pos;
            if (remLen > 0 && remLen <= 9 && S[pos] != '0') {
                int an;
                if (parse_upto9(S, pos, remLen, an)) {
                    if (an % t == 0) {
                        int f = an / t;
                        if (f >= 2) bestF = min(bestF, f);
                    }
                }
            }

            long long nextv = (long long)t + (long long)d;

            if (nextv >= 1000000000LL) break;
            string ns = to_string((int)nextv);
            if (pos + (int)ns.size() > L) break;
            if (S.compare(pos, (int)ns.size(), ns) != 0) break;
            pos += (int)ns.size();
            t = (int)nextv;
            if (pos >= L) break;
        }
    };

    for (int l1 = 1; l1 <= 9 && l1 <= L - 2; ++l1) {
        for (int l2 = 1; l2 <= 9 && l1 + l2 <= L - 1; ++l2) {
            try_pair(l1, l2);
        }
    }

    if (bestF == INT_MAX) cout << 0 << '\n';
    else cout << bestF << '\n';
    return 0;
}