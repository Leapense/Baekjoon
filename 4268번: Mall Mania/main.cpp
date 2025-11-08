#include <bits/stdc++.h>
using namespace std;

static unsigned char vis[2001][2001];
static unsigned char isB[2001][2001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    auto read_points = [&](int p) {
        vector<pair<int, int>> v;
        v.reserve(p);
        for (int i = 0; i < p; ++i) {
            int a, s;
            cin >> a >> s;
            v.emplace_back(a, s);
        }
        return v;
    };

    int p1;
    while ((cin >> p1)) {
        if (p1 == 0) break;
        auto A = read_points(p1);

        int p2;
        cin >> p2;
        auto B = read_points(p2);

        memset(vis, 0, sizeof(vis));
        memset(isB, 0, sizeof(isB));

        for (auto [x, y] : B) {
            if (0 <= x && x <= 2000 && 0 <= y && y <= 2000) {
                isB[y][x] = 1;
            }
        }

        queue<pair<int, int>> q;
        bool immediate = false;
        for (auto [x, y] : A) {
            if (x < 0 || x > 2000 || y < 0 || y > 2000) continue;
            if (!vis[y][x]) {
                q.emplace(x, y);
                if (isB[y][x]) {
                    cout << 0 << "\n";
                    immediate = true;
                    break;
                }
            }
        }
        if (immediate) continue;

        const int dx[4] = {1, -1, 0, 0};
        const int dy[4] = {0, 0, 1, -1};

        int dist = 0;
        while (!q.empty()) {
            int qs = (int)q.size();
            while (qs--) {
                auto [x, y] = q.front(); q.pop();
                for (int dir = 0; dir < 4; ++dir) {
                    int nx = x + dx[dir], ny = y + dy[dir];
                    if (nx < 0 || nx > 2000 || ny < 0 || ny > 2000) continue;
                    if (vis[ny][nx]) continue;
                    if (isB[ny][nx]) { cout << (dist + 1) << "\n"; goto next_case; }
                    vis[ny][nx] = 1;
                    q.emplace(nx, ny);
                }
            }
            ++dist;
        }

        cout << dist << "\n";
        next_case:
        ;
    }

    return 0;
}