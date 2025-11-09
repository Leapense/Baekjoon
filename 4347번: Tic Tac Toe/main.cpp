#include <bits/stdc++.h>
using namespace std;

static bool win(const array<string,3>& b, char p) {
    for (int r = 0; r < 3; ++r) {
        if (b[r][0] == p && b[r][1] == p && b[r][2] == p) return true;
    }
    for (int c = 0; c < 3; ++c) {
        if (b[0][c] == p && b[1][c] == p && b[2][c] == p) return true;
    }

    if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
    if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;
    for (int tc = 0; tc < N; ++tc) {
        array<string, 3> b{};
        for (int i = 0; i < 3; ++i) cin >> b[i];

        int x = 0, o = 0;
        for (auto &row : b) {
            for (char ch : row) {
                if (ch == 'X') ++x;
                else if (ch == 'O') ++o;
            }
        }
        
        bool cnt_ok = (x == o) || (x == o + 1);
        bool wx = win(b, 'X');
        bool wo = win(b, 'O');

        bool ok;
        if (!cnt_ok) ok = false;
        else if (wx && wo) ok = false;
        else if (wx) ok = (x == o + 1);
        else if (wo) ok = (x == o);
        else ok = true;

        cout << (ok ? "yes" : "no") << '\n';
    }

    return 0;
}