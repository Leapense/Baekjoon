// C++26 solution for "Takistusrada" (Obstacle track)
// BFS over (x,y,vx,vy) with lattice-point segment intersection checking.

#include <bits/stdc++.h>
using namespace std;

static int igcd(int a, int b) {
    a = abs(a); b = abs(b);
    while (b) { int t = a % b; a = b; b = t; }
    return a;
}

struct P { int x, y; };

static bool has_obstacle(const vector<P>& obs, int x, int y) {
    for (auto &p : obs) if (p.x == x && p.y == y) return true;
    return false;
}

static bool safe_move(const vector<P>& obs, int x, int y, int dx, int dy) {
    if (dx == 0 && dy == 0) {
        return !has_obstacle(obs, x, y);
    }
    int g = igcd(dx, dy);
    int stepx = dx / g;
    int stepy = dy / g;
    for (int i = 1; i <= g; i++) {
        int px = x + stepx * i;
        int py = y + stepy * i;
        if (has_obstacle(obs, px, py)) return false;
    }
    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int tx, ty, N;
    cin >> tx >> ty >> N;
    vector<P> obs(N);
    for (int i = 0; i < N; i++) cin >> obs[i].x >> obs[i].y;

    if (has_obstacle(obs, 0, 0)) {
        cout << -1 << "\n";
        return 0;
    }
    if (tx == 0 && ty == 0) {
        cout << 0 << "\n";
        return 0;
    }
    if (has_obstacle(obs, tx, ty)) {
        cout << -1 << "\n";
        return 0;
    }

    constexpr int L = 60;
    constexpr int W = 2*L + 1;
    constexpr int V = 15; // -7..7
    const int STATES = W * W * V * V;

    auto idx = [](int x, int y, int vx, int vy) -> int {
        int xi = x + L;
        int yi = y + L;
        int vxi = vx + 7;
        int vyi = vy + 7;
        return (((xi * W + yi) * V + vxi) * V + vyi);
    };

    vector<int> dist(STATES, -1);
    deque<int> q;

    int s = idx(0,0,0,0);
    dist[s] = 0;
    q.push_back(s);

    while (!q.empty()) {
        int cur = q.front(); q.pop_front();
        int t = dist[cur];

        int tmp = cur;
        int vyi = tmp % V; tmp /= V;
        int vxi = tmp % V; tmp /= V;
        int pos = tmp;
        int yi = pos % W;
        int xi = pos / W;

        int x = xi - L;
        int y = yi - L;
        int vx = vxi - 7;
        int vy = vyi - 7;

        for (int a = 0; a < 5; a++) {
            int nvx = vx, nvy = vy;
            if (a == 1) { if (nvx == 7) continue; nvx++; }
            else if (a == 2) { if (nvx == -7) continue; nvx--; }
            else if (a == 3) { if (nvy == 7) continue; nvy++; }
            else if (a == 4) { if (nvy == -7) continue; nvy--; }

            int nx = x + nvx;
            int ny = y + nvy;

            if (nx < -L || nx > L || ny < -L || ny > L) continue;
            if (!safe_move(obs, x, y, nvx, nvy)) continue;

            int ni = idx(nx, ny, nvx, nvy);
            if (dist[ni] != -1) continue;

            dist[ni] = t + 1;
            if (nx == tx && ny == ty) {
                cout << dist[ni] << "\n";
                return 0;
            }
            q.push_back(ni);
        }
    }

    cout << -1 << "\n";
    return 0;
}