#include <bits/stdc++.h>
using namespace std;

static int board[8][7];
static bool filled[8][7];
static bool used[7][7];
static unsigned long long ways = 0ULL;

inline void skip_to_next(int &pos) {
    while (pos < 56) {
        int r = pos / 7, c = pos % 7;
        if (!filled[r][c]) break;
        ++pos;
    }
}

void dfs(int pos) {
    skip_to_next(pos);
    if (pos == 56) {
        ++ways;
        return;
    }

    int r = pos / 7, c = pos % 7;

    if (c + 1 < 7 && !filled[r][c + 1]) {
        int a = board[r][c], b = board[r][c + 1];
        int x = min(a, b), y = max(a, b);
        if (!used[x][y]) {
            used[x][y] = true;
            filled[r][c] = filled[r][c + 1] = true;
            dfs(pos + 1);
            filled[r][c] = filled[r][c + 1] = false;
            used[x][y] = false;
        }
    }

    if (r + 1 < 8 && !filled[r + 1][c]) {
        int a = board[r][c], b = board[r + 1][c];
        int x = min(a, b), y = max(a, b);
        if (!used[x][y]) {
            used[x][y] = true;
            filled[r][c] = filled[r + 1][c] = true;
            dfs(pos + 1);
            filled[r][c] = filled[r + 1][c] = false;
            used[x][y] = false;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int i = 0; i < 8; ++i) {
        string s; cin >> s;
        for (int j = 0; j < 7; ++j) board[i][j] = s[j] - '0';
    }
    memset(filled, 0, sizeof(filled));
    memset(used, 0, sizeof(used));
    
    dfs(0);
    cout << ways << '\n';
    return 0;
}