#include <bits/stdc++.h>
using namespace std;

struct SAM {
    static constexpr int ALPHA = 128;
    struct State {
        int next[ALPHA];
        int link = -1;
        int len = 0;
        State() { fill(begin(next), end(next), -1); }
    };

    vector<State> st;
    int last;
    SAM(int maxlen = 2005) { st.reserve(2 * maxlen); st.emplace_back(); last = 0; }

    void extend(unsigned char c) {
        int cur = (int)st.size();
        st.emplace_back();
        st[cur].len = st[last].len + 1;
        int p = last;
        while (p != -1 && st[p].next[c] == -1) {
            st[p].next[c] = cur;
            p = st[p].link;
        }
        if (p == -1) {
            st[cur].link = 0;
        } else {
            int q = st[p].next[c];
            if (st[p].len + 1 == st[q].len) {
                st[cur].link = q;
            } else {
                int clone = (int)st.size();
                st.push_back(st[q]);
                st[clone].len = st[p].len + 1;
                while (p != -1 && st[p].next[c] == q) {
                    st[p].next[c] = clone;
                    p = st[p].link;
                }

                st[q].link = st[cur].link = clone;
            }
        }
        last = cur;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    string S, P;
    if (!(cin >> S)) return 0;
    cin >> P;
    SAM sam((int)S.size() + 5);
    for (unsigned char c : S) sam.extend(c);

    int n = (int)P.size();
    vector<int> endLen(n, 0);
    int v = 0, l = 0;
    for (int i = 0; i < n; ++i) {
        unsigned char c = (unsigned char)P[i];
        if (sam.st[v].next[c] != -1) {
            v = sam.st[v].next[c];
            ++l;
        } else {
            while (v != -1 && sam.st[v].next[c] == -1) {
                v = sam.st[v].link;
                if (v == -1) break;
                l = sam.st[v].len;
            }
            if (v == -1) { v = 0; l = 0; }
            else { v = sam.st[v].next[c]; ++l; }
        }
        endLen[i] = l;
    }

    vector<int> L(n, 0);
    vector<int> B(n);
    for (int r = 0; r < n; ++r) B[r] = endLen[r] - r;

    deque<int> dq;
    int j = 0;
    for (int i = 0; i < n; ++i) {
        while (j < n) {
            while (!dq.empty() && B[dq.back()] >= B[j]) dq.pop_back();
            dq.push_back(j);
            if (B[dq.front()] >= 1 - i) {
                ++j;
            } else {
                break;
            }
        }

        L[i] = j - i;
        if (!dq.empty() && dq.front() == i) dq.pop_front();
    }

    const int INF = 1e9;
    vector<int> dp(n + 1, INF);
    dp[n] = 0;
    for (int i = n - 1; i >= 0; --i) {
        int best = INF;
        for (int t = 1; t <= L[i]; ++t) best = min(best, dp[i + t]);
        dp[i] = 1 + best;
    }
    cout << dp[0] << "\n";
    return 0;
}