#include <iostream>
#include <vector>
#include <queue>
#include <limits>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int T, C, Ts, Te;
    if (!(std::cin >> T >> C >> Ts >> Te)) {
        return 0;
    }

    std::vector<std::vector<std::pair<int, int>>> adj(T + 1);
    for (int i = 0; i < C; ++i) {
        int a, b, c;
        std::cin >> a >> b >> c;
        adj[a].push_back({b, c});
        adj[b].push_back({a, c});
    }

    const long long INF = std::numeric_limits<long long>::max();
    std::vector<long long> dist(T + 1, INF);
    std::vector<bool> visited(T + 1, false);

    using Node = std::pair<long long, int>;
    std::priority_queue<Node, std::vector<Node>, std::greater<Node>> pq;
    dist[Ts] = 0;
    pq.push({0, Ts});

    while (!pq.empty()) {
        Node cur = pq.top();
        pq.pop();

        long long d = cur.first;
        int u = cur.second;

        if (visited[u]) continue;
        visited[u] = true;
        if (u == Te) break;

        for (const auto &edge : adj[u]) {
            int v = edge.first;
            int w = edge.second;
            if (visited[v]) continue;
            long long nd = d + w;
            if (nd < dist[v]) {
                dist[v] = nd;
                pq.push({nd, v});
            }
        }
    }

    std::cout << dist[Te] << '\n';
    return 0;
}