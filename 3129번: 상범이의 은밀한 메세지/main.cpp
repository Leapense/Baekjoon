#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstring>

using namespace std;

constexpr int ALPHABET_SIZE = 26;
constexpr inline int toI(char c) noexcept { return c - 'a'; }
constexpr inline char toC(int x) noexcept { return x + 'a'; }

int min_period(const vector<int>& d) {
    int L = d.size();
    if (L <= 1) return 0;

    vector<int> failure(L, 0);
    for (int i = 1, j = 0; i < L; ++i) {
        while (j > 0 && d[i] != d[j]) {
            j = failure[j - 1];
        }
        if (d[i] == d[j]) {
            failure[i] = ++j;
        }
    }

    int period = L - failure[L - 1];
    if (L % period == 0 && failure[L - 1] > 0) {
        return period;
    }

    for (int k = 1; k * 2 <= L; ++k) {
        bool ok = true;
        for (int i = k; i < L; ++i) {
            if (d[i] != d[i - k]) {
                ok = false;
                break;
            }
        }

        if (ok) return k;
    }

    return 0;
}

bool kmp_search(const string& text, const string& pattern) {
    int n = text.size();
    int m = pattern.size();

    if (m > n) return false;
    if (m == 0) return true;

    vector<int> failure(m, 0);
    for (int i = 1, j = 0; i < m; ++i) {
        while (j > 0 && pattern[i] != pattern[j]) {
            j = failure[j - 1];
        }
        if (pattern[i] == pattern[j]) {
            failure[i] = ++j;
        }
    }

    for (int i = 0, j = 0; i < n; ++i) {
        while (j > 0 && text[i] != pattern[j]) {
            j = failure[j - 1];
        }
        if (text[i] == pattern[j]) {
            if (++j == m) return true;
        }
    }

    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string C, S;
    if (!(cin >> C >> S)) return 0;

    const int N = C.size();
    const int L = S.size();

    if (L > N) return 0;

    vector<int> Ci, Si;
    Ci.reserve(N);
    Si.reserve(L);

    for (char c : C) Ci.push_back(toI(c));
    for (char c : S) Si.push_back(toI(c));

    vector<int> D(L);
    vector<int> K;

    string P;
    P.reserve(N);

    for (int s = 0; s + L <= N; ++s) {
        for (int i = 0; i < L; ++i) {
            int v = Ci[s + i] - Si[i];
            D[i] = (v + ALPHABET_SIZE) % ALPHABET_SIZE;
        }

        int k = min_period(D);
        if (k == 0) continue;

        K.assign(k, -1);
        bool ok = true;

        if ((k & (k - 1)) == 0) {
            int mask = k - 1;
            for (int i = 0; i < L; ++i) {
                int t = (s + i) & mask;
                if (K[t] == -1) {
                    K[t] = D[i];
                } else if (K[t] != D[i]) {
                    ok = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < L; ++i) {
                int t = (s + i) % k;
                if (K[t] == -1) {
                    K[t] = D[i];
                } else if (K[t] != D[i]) {
                    ok = false;
                    break;
                }
            }
        }

        if (!ok) continue;

        P.clear();
        for (int i = 0; i < N; ++i) {
            int v = (Ci[i] - K[i % k] + ALPHABET_SIZE) % ALPHABET_SIZE;
            P.push_back(toC(v));
        }

        if (kmp_search(P, S)) {
            cout << P << '\n';
            return 0;
        }
    }

    return 0;
}