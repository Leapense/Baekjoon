#include <stdio.h>
#include <limits.h>

#define MAXN 100
#define SHAPES 13
#define CELLS 4

static int board[MAXN][MAXN];

// shapes[s][k][0] = row offset, shapes[s][k][1] = col offset
static const int shapes[SHAPES][CELLS][2] = {
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

int main(void) {
    int N;
    int tc = 1;

    while (scanf("%d", &N) == 1 && N != 0) {
        // 격자 입력
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                scanf("%d", &board[i][j]);
            }
        }

        long long best = LLONG_MIN;

        // 모든 시작 위치 (i, j)에 대해
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                // 모든 모양에 대해
                for (int s = 0; s < SHAPES; ++s) {
                    long long sum = 0;
                    int ok = 1;

                    for (int k = 0; k < CELLS; ++k) {
                        int ni = i + shapes[s][k][0];
                        int nj = j + shapes[s][k][1];

                        if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                            ok = 0;
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

        printf("%d. %lld\n", tc, best);
        ++tc;
    }

    return 0;
}