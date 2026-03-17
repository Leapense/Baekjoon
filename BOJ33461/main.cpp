#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<vector<bool>> attacked(N, vector<bool>(N, false));

    auto mark = [&](int r, int c) {
        if (0 <= r && r < N && 0 <= c && c < N) {
            attacked[r][c] = true;
        }
    };

    const int kr[8] = {-2, -2, -1, -1, 1, 1, 2, 2};
    const int kc[8] = {-1, 1, -2, 2, -2, 2, -1, 1};

    for (int i = 0; i < M; ++i) {
        char type;
        int r, c;
        cin >> type >> r >> c;
        --r; --c;

        mark(r, c);
        if (type == 'N') {
            for (int k = 0; k < 8; ++k) {
                mark(r + kr[k], c + kc[k]);
            }
        } else {
            for (int x = 0; x < N; ++x) {
                mark(r, x);
                mark(x, c);
            }

            if (type == 'Q') {
                for (int dr : {-1, 1}) {
                    for (int dc : {-1, 1}) {
                        int nr = r, nc = c;
                        while (0 <= nr && nr < N && 0 <= nc && nc < N) {
                            mark(nr, nc);
                            nr += dr;
                            nc += dc;
                        }
                    }
                }
            }
        }
    }

    int answer = 0;
    for (int r = 0; r < N; ++r) {
        for (int c = 0; c < N; ++c) {
            if (attacked[r][c]) ++answer;
        }
    }

    cout << answer;
    return 0;
}