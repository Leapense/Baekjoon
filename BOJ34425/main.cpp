#include <bits/stdc++.h>
using namespace std;

static constexpr int DIG_MASK[10] = {
    119,
    36,
    93,
    109,
    46,
    107,
    123,
    37,
    127,
    47
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    array<array<char, 7>, 6> state{};
    for (int i = 0; i < 6; ++i) {
        for (int s = 0; s < 7; ++s) {
            string tok;
            cin >> tok;
            state[i][s] = tok[0];
        }
    }

    array<array<bool, 10>, 6> possible{};
    for (int pos = 0; pos < 6; ++pos) {
        for (int d = 0; d <= 9; ++d) {
            bool ok = true;
            int mask = DIG_MASK[d];
            for (int s = 0; s < 7; ++s) {
                int on = (mask >> s) & 1;
                if (state[pos][s] == '1' && !on) { ok = false; break; }
                if (state[pos][s] == '0' && on) { ok = false; break; }
            }

            possible[pos][d] = ok;
        }
    }

    for (int h = 0; h <= 23; ++h) {
        int h1 = h / 10, h2 = h % 10;
        if (!possible[0][h1] || !possible[1][h2]) continue;

        for (int m = 0; m <= 59; ++m) {
            int m1 = m / 10, m2 = m % 10;
            if (!possible[2][m1] || !possible[3][m2]) continue;

            for (int s = 0; s <= 59; ++s) {
                int s1 = s / 10, s2 = s % 10;
                if (!possible[4][s1] || !possible[5][s2]) continue;

                cout << setw(2) << setfill('0') << h << ":"
                     << setw(2) << setfill('0') << m << ":"
                     << setw(2) << setfill('0') << s << "\n";
            }
        }
    }

    return 0;
}