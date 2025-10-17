#include <bits/stdc++.h>
using namespace std;

struct Fenwick {
    int n;
    vector<int> bit;
    Fenwick(int n = 0): n(n), bit(n + 1, 0) {}
    void add(int i, int v) { for (; i <= n; i += i & -i) bit[i] += v; }
    int sum(int i) const { int s = 0; for (; i > 0; i -= i & -i) s += bit[i]; return s; }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    vector<pair<long long, long long>> seg(N);
    for (int i = 0; i < N; ++i) cin >> seg[i].first >> seg[i].second;

    sort(seg.begin(), seg.end(), [](auto& a, auto& b) {
        return a.first < b.first;
    });

    vector<long long> rights;
    rights.reserve(N);
    for (auto &p : seg) rights.push_back(p.second);
    sort(rights.begin(), rights.end());
    rights.erase(unique(rights.begin(), rights.end()), rights.end());

    unordered_map<long long, int> rankR;
    rankR.reserve(N * 2);
    for (int i = 0; i < (int)rights.size(); ++i) rankR[rights[i]] = i + 1;

    Fenwick fw((int)rights.size());
    int best = 0;

    for (int i = N - 1; i >= 0; --i) {
        int r = rankR[seg[i].second];
        int cnt = fw.sum(r - 1);
        if (cnt > best) best = cnt;
        fw.add(r, 1);
    }

    cout << best << '\n';
    return 0;
}