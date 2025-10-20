#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    vector<vector<int>> adj(N + 1);
    while (true) {
        int a, b;
        if (!(cin >> a >> b)) break;
        if (a == -1 && b == -1) break;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    const int INF = 1e9;
    vector<int> score(N + 1, 0);
    int best = INF;

    for (int s = 1; s <= N; ++s) {
        vector<int> dist(N + 1, INF);
        queue<int> q;
        dist[s] = 0;
        q.push(s);

        while (!q.empty()) {
            int u = q.front(); q.pop();
            for (int v : adj[u]) {
                if (dist[v] == INF) {
                    dist[v] = dist[u] + 1;
                    q.push(v);
                }
            }
        }

        int ecc = 0;
        for (int i = 1; i <= N; ++i) ecc = max(ecc, dist[i]);
        score[s] = ecc;
        best = min(best, ecc);
    }

    vector<int> cand;
    for (int i = 1; i <= N; ++i) {
        if (score[i] == best) {
            cand.push_back(i);
        }
    }

    cout << best << ' ' << cand.size() << '\n';
    for (size_t i = 0; i < cand.size(); ++i) {
        if (i) cout << ' ';
        cout << cand[i];
    }

    cout << '\n';
    return 0;
}