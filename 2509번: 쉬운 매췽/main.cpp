#include <bits/stdc++.h>
using namespace std;

static inline vector<int> prefixSums(const vector<int>& t) {
    int n = (int)t.size();
    vector<int> S(n + 1, 0);
    for (int i = 1; i <= n; ++i) S[i] = S[i - 1] + t[i - 1];
    return S;
}

static inline vector<int> cum(const vector<int>& p) {
    vector<int> c(p.size());
    int s = 0;
    for (size_t i = 0; i < p.size(); ++i) { s += p[i]; c[i] = s; }
    return c;
}

static inline vector<int> getEndIndicesForPattern(
    const vector<int>& S,
    const vector<int>& cumP,
    const vector<int>& sum2idx
) {
    int N = (int)S.size() - 1;
    int total = S[N];
    vector<int> ends;

    for (int k = 1; k <= N; ++k) {
        if (total - S[k - 1] < cumP.back()) break;
        int idx = k - 1;
        bool ok = true;
        for (int need : cumP) {
            int target = S[k - 1] + need;
            if (target > total) { ok = false; break; }
            int pos = sum2idx[target];
            if (pos == -1 || pos <= idx) { ok = false; break; }
            idx = pos;
        }
        if (ok) ends.push_back(idx);
    }
    return ends;
}

static inline vector<int> getStartPrefixIndices(
    const vector<int>& S,
    const vector<int>& cumP,
    const vector<int>& sum2idx
) {
    int N = (int)S.size() - 1;
    int total = S[N];
    vector<int> starts_j;
    for (int s = 1; s <= N; ++s) {
        if (total - S[s - 1] < cumP.back()) break;
        int idx = s - 1;
        bool ok = true;
        for (int need : cumP) {
            int target = S[s - 1] + need;
            if (target > total) { ok = false; break; }
            int pos = sum2idx[target];
            if (pos == -1 || pos <= idx) { ok = false; break; }
            idx = pos;
        }
        if (ok) starts_j.push_back(s - 1);
    }
    return starts_j;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int TC;
    if (!(cin >> TC)) return 0;
    while (TC--) {
        int N;
        cin >> N;
        vector<int> T(N);
        for (int i = 0; i < N; ++i) cin >> T[i];

        int M1;
        cin >> M1;
        vector<int> P1(M1);
        for (int i = 0; i < M1; ++i) cin >> P1[i];

        int M2;
        cin >> M2;
        vector<int> P2(M2);
        for (int i = 0; i < M2; ++i) cin >> P2[i];

        vector<int> S = prefixSums(T);
        int total = S.back();
        vector<int> sum2idx(total + 1, -1);
        for (int i = 0; i < (int)S.size(); ++i) sum2idx[S[i]] = i;

        vector<int> C1 = cum(P1);
        vector<int> C2 = cum(P2);

        vector<int> R = (M1 ? getEndIndicesForPattern(S, C1, sum2idx) : vector<int>());
        vector<int> J = (M2 ? getStartPrefixIndices(S, C2, sum2idx) : vector<int>());

        int ans1 = (int)R.size();
        int ans2 = (int)J.size();

        int bestN = 1;
        int bestCnt = 0;
        if (!R.empty() && !J.empty()) {
            vector<int> freq(total + 1, 0);
            for (int r : R) {
                for (int j : J) {
                    if (j <= r) continue;
                    int d = S[j] - S[r];
                    ++freq[d];
                }
            }

            for (int d = 1; d <= total; ++d) {
                if (freq[d] > bestCnt) {
                    bestCnt = freq[d];
                    bestN = d;
                }
            }
            if (bestCnt == 0) bestN = 1;
        }

        cout << ans1 << ' ' << ans2 << ' ' << bestN << ' ' << bestCnt << '\n';
    }
    return 0;
}