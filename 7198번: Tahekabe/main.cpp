#include <bits/stdc++.h>
using namespace std;

static const int dr[4] = {-1, 1, 0, 0};
static const int dc[4] = {0, 0, -1, 1};

struct Solver {
    int N;
    vector<string> board;
    bool vis[20][20]{};

    bool dfs(int r, int c, const string& w, int idx) {
        if (idx == (int)w.size()) return true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (vis[nr][nc]) continue;
            if (board[nr][nc] != w[idx]) continue;

            vis[nr][nc] = true;
            if (dfs(nr, nc, w, idx + 1)) return true;
            vis[nr][nc] = false;
        }
        return false;
    }

    bool canMake(int sr, int sc, const string& w) {
        memset(vis, 0, sizeof(vis));
        for (int d = 0; d < 4; d++) {
            int nr = sr + dr[d];
            int nc = sc + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (board[nr][nc] != w[0]) continue;
            vis[nr][nc] = true;
            if (dfs(nr, nc, w, 1)) return true;
            vis[nr][nc] = false;
        }

        return false;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, R, V;
    cin >> N >> R >> V;
    int sr = R - 1, sc = V - 1;

    vector<string> board(N);
    for (int i = 0; i < N; i++) cin >> board[i];

    int K;
    cin >> K;
    vector<string> words(K);
    for (int i = 0; i < K; i++) cin >> words[i];

    Solver s;
    s.N = N;
    s.board = board;

    bool any = false;
    for (const auto& w : words) {
        if (s.canMake(sr, sc, w)) {
            cout << w << "\n";
            any = true;
        }
    }
    if (!any) cout << "EI SAA\n";
    return 0;
}