#include <bits/stdc++.h>
using namespace std;

static inline bool inb(int r, int c) { return 0 <= r && r < 8 && 0 <= c && c < 8; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string fen;
    while (getline(cin, fen)) {
        if (fen.empty()) continue;

        array<array<char,8>,8> board{};
        for (auto &row : board) row.fill('.');

        int r = 0, c = 0;
        for (char ch : fen) {
            if (ch == '/') { r++; c = 0; continue; }
            if (isdigit(static_cast<unsigned char>(ch))) {
                int k = ch - '0';
                for (int i = 0; i < k; ++i) board[r][c++] = '.';
            } else {
                board[r][c++] = ch;
            }
        }

        bool occupied[8][8] = {};
        bool attacked[8][8] = {};
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                occupied[i][j] = (board[i][j] != '.');

        auto mark = [&](int rr, int cc) {
            if (inb(rr, cc)) attacked[rr][cc] = true;
        };

        const int K8[8][2] = { {-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1} };
        const int N8[8][2] = { {-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1} };
        const int B4[4][2] = { {-1,-1},{-1,1},{1,-1},{1,1} };
        const int R4[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };

        auto slide = [&](int sr, int sc, const int dirs[][2], int ndir) {
            for (int d = 0; d < ndir; ++d) {
                int dr = dirs[d][0], dc = dirs[d][1];
                int rr = sr + dr, cc = sc + dc;
                while (inb(rr, cc)) {
                    attacked[rr][cc] = true;
                    if (occupied[rr][cc]) break;
                    rr += dr; cc += dc;
                }
            }
        };

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char p = board[i][j];
                if (p == '.') continue;
                if (p == 'P') { // white pawn
                    mark(i-1, j-1); mark(i-1, j+1);
                } else if (p == 'p') { // black pawn
                    mark(i+1, j-1); mark(i+1, j+1);
                } else if (p == 'N' || p == 'n') {
                    for (auto [dr, dc] : N8) mark(i+dr, j+dc);
                } else if (p == 'K' || p == 'k') {
                    for (auto [dr, dc] : K8) mark(i+dr, j+dc);
                } else if (p == 'B' || p == 'b') {
                    slide(i, j, B4, 4);
                } else if (p == 'R' || p == 'r') {
                    slide(i, j, R4, 4);
                } else if (p == 'Q' || p == 'q') {
                    slide(i, j, B4, 4);
                    slide(i, j, R4, 4);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                if (!occupied[i][j] && !attacked[i][j]) ++ans;

        cout << ans << '\n';
    }
    return 0;
}