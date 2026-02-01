#include <bits/stdc++.h>
using namespace std;

static bool win(const array<array<char, 3>, 3>& b, char p) {
    for (int r = 0; r < 3; r++) {
        if (b[r][0] == p && b[r][1] == p && b[r][2] == p) return true;
    }

    for (int c = 0; c < 3; c++) {
        if (b[0][c] == p && b[1][c] == p && b[2][c] == p) return true;
    }

    if (b[0][0] == p && b[1][1] == p && b[2][2] == p) return true;
    if (b[0][2] == p && b[1][1] == p && b[2][0] == p) return true;
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    char p;
    cin >> p;

    array<array<char, 3>, 3> b{};
    for (int r = 0; r < 3; r++) {
        string s;
        cin >> s;
        for (int c = 0; c < 3; c++) {
            b[r][c] = s[c];
        }
    }

    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            if (b[r][c] != 'E') continue;
            auto tmp = b;
            tmp[r][c] = p;
            if (win(tmp, p)) {
                cout << (r + 1) << ' ' << (c + 1) << '\n';
                return 0;
            }
        }
    }

    return 0;
}