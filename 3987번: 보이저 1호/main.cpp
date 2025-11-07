#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N, M;
    if (!(cin >> N >> M)) return 0;
    vector<string> g(N);
    for (int i = 0; i < N; ++i) cin >> g[i];
    int PR, PC; cin >> PR >> PC;
    --PR; --PC;

    const int U=0, R=1, D=2, L=3;
    const int dr[4] = {-1, 0, 1, 0};
    const int dc[4] = {0, 1, 0, -1};
    const int slash[4] = {R, U, L, D};
    const int back_[4] = {L, D, R, U};
    const char dirChar[4] = {'U','R','D','L'};

    auto simulate = [&](int d)->pair<bool,long long> {
        static vector vis(0, vector<vector<char>>(0, vector<char>(0)));
        vis.assign(N, vector<vector<char>>(M, vector<char>(4, 0)));
        long long t = 0;
        int r = PR, c = PC, dir = d;

        while (true) {
            r += dr[dir]; c += dc[dir]; ++t;
            if (r < 0 || r >= N || c < 0 || c >= M) return {false, t};
            char ch = g[r][c];
            if (ch == 'C') return {false, t};
            if (ch == '/') dir = slash[dir];
            else if (ch == '\\') dir = back_[dir];

            if (vis[r][c][dir]) return {true, -1};
            vis[r][c][dir] = 1;
        }
    };

    bool anyInf = false;
    long long bestTime = -1;
    int bestDir = 0;
    for (int d = 0; d < 4; ++d) {
        auto [inf, t] = simulate(d);
        if (inf) {
            if (!anyInf) { anyInf = true; bestDir = d; }
        } else if (!anyInf) {
            if (t > bestTime) { bestTime = t; bestDir = d; }
        }
    }

    cout << dirChar[bestDir] << '\n';
    if (anyInf) cout << "Voyager\n";
    else cout << bestTime << '\n';
    return 0;
}