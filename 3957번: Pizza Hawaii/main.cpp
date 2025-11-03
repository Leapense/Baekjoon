// C++26
#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T; 
    if (!(cin >> T)) return 0;
    for (int tc = 0; tc < T; ++tc) {
        int n; cin >> n;

        unordered_map<string, uint64_t> Fmask, Gmask;
        Fmask.reserve(n * 40); Gmask.reserve(n * 40);

        for (int i = 0; i < n; ++i) {
            string name; cin >> name;
            int m;

            cin >> m;
            for (int j = 0; j < m; ++j) {
                string w; cin >> w;
                Fmask[w] |= (1ULL << i);
            }

            cin >> m;
            for (int j = 0; j < m; ++j) {
                string w; cin >> w;
                Gmask[w] |= (1ULL << i);
            }
        }

        struct Item { uint64_t mask; string w; };
        vector<Item> F, G;
        F.reserve(Fmask.size()); G.reserve(Gmask.size());
        for (auto &kv : Fmask) F.push_back({kv.second, kv.first});
        for (auto &kv : Gmask) G.push_back({kv.second, kv.first});

        auto cmp = [](const Item& a, const Item& b){
            if (a.mask != b.mask) return a.mask < b.mask;
            return a.w < b.w;
        };
        sort(F.begin(), F.end(), cmp);
        sort(G.begin(), G.end(), cmp);

        vector<pair<string,string>> ans;
        size_t i = 0, j = 0;
        while (i < F.size() && j < G.size()) {
            if (F[i].mask < G[j].mask) {
                uint64_t m = F[i].mask;
                while (i < F.size() && F[i].mask == m) ++i;
            } else if (F[i].mask > G[j].mask) {
                uint64_t m = G[j].mask;
                while (j < G.size() && G[j].mask == m) ++j;
            } else {
                uint64_t m = F[i].mask;
                size_t i2 = i, j2 = j;
                while (i2 < F.size() && F[i2].mask == m) ++i2;
                while (j2 < G.size() && G[j2].mask == m) ++j2;
                for (size_t a = i; a < i2; ++a)
                    for (size_t b = j; b < j2; ++b)
                        ans.emplace_back(F[a].w, G[b].w);
                i = i2; j = j2;
            }
        }

        sort(ans.begin(), ans.end(), [](auto &p, auto &q){
            if (p.first != q.first) return p.first < q.first;
            return p.second < q.second;
        });

        for (auto &p : ans) {
            cout << '(' << p.first << ", " << p.second << ")\n";
        }
        if (tc + 1 < T) cout << "\n";
    }
    return 0;
}