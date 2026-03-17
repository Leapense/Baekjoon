#include <stdio.h>
#include <stdbool.h>

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) return 0;

    bool attacked[200][200] = {false};

    const int kr[8] = {-2, -2, -1, -1, 1, 1, 2, 2};
    const int kc[8] = {-1, 1, -2, 2, -2, 2, -1, 1};

    for (int i = 0; i < M; ++i) {
        char type;
        int r, c;
        scanf(" %c %d %d", &type, &r, &c);
        --r; --c;

        attacked[r][c] = true;

        if (type == 'N') {
            for (int k = 0; k < 8; ++k) {
                int nr = r + kr[k];
                int nc = c + kc[k];
                if (0 <= nr && nr < N && 0 <= nc && nc < N) {
                    attacked[nr][nc] = true;
                }
            }
        } else {
            for (int x = 0; x < N; ++x) {
                attacked[r][x] = true;
                attacked[x][c] = true;
            }
            if (type == 'Q') {
                for (int dr = -1; dr <= 1; dr += 2) {
                    for (int dc = -1; dc <= 1; dc += 2) {
                        int nr = r;
                        int nc = c;
                        while (0 <= nr && nr < N && 0 <= nc && nc < N) {
                            attacked[nr][nc] = true;
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

    printf("%d", answer);
    return 0;
}