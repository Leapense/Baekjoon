#include <iostream>
#include <limits>

using namespace std;

constexpr int MAXN = 100;
constexpr int SHAPES = 13;
constexpr int CELLS = 4;

int board[MAXN][MAXN];

constexpr int shapes[SHAPES][CELLS][2] = {
    // 1. I 블록
    { {0,0}, {0,1}, {0,2}, {0,3} },
    { {0,0}, {1,0}, {2,0}, {3,0} },

    // 2. 두 번째 블록 (회전 2개)
    { {0,0}, {0,1}, {1,1}, {1,2} },
    { {0,1}, {1,0}, {1,1}, {2,0} },

    // 3. 세 번째 블록 (회전 4개)
    { {0,1}, {1,1}, {2,0}, {2,1} },
    { {0,0}, {0,1}, {0,2}, {1,2} },
    { {0,0}, {0,1}, {1,0}, {2,0} },
    { {0,0}, {1,0}, {1,1}, {1,2} },

    // 4. T 블록 (회전 4개)
    { {0,0}, {0,1}, {0,2}, {1,1} },
    { {0,1}, {1,0}, {1,1}, {2,1} },
    { {0,1}, {1,0}, {1,1}, {1,2} },
    { {0,0}, {1,0}, {1,1}, {2,0} },

    // 5. 정사각형
    { {0,0}, {0,1}, {1,0}, {1,1} }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    int tc = 1;
    while ((cin >> N) && N != 0) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                cin >> board[i][j];
            }
        }

        long long best = numeric_limits<long long>::min();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                for (int s = 0; s < SHAPES; ++s) {
                    long long sum = 0;
                    bool ok = true;

                    for (int k = 0; k < CELLS; ++k) {
                        int ni = i + shapes[s][k][0];
                        int nj = j + shapes[s][k][1];

                        if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                            ok = false;
                            break;
                        }
                        sum += board[ni][nj];
                    }

                    if (ok && sum > best) {
                        best = sum;
                    }
                }
            }
        }
        cout << tc++ << ". " << best << "\n";
    }

    return 0;
}