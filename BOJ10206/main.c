#include <stdio.h>
#include <string.h>
#include <float.h>

static int note_index(char c) {
    switch(c) {
        case 'C': return 0;
        case 'D': return 2;
        case 'E': return 4;
        case 'F': return 5;
        case 'G': return 7;
        case 'A': return 9;
        case 'B': return 11;
        default: return -1;
    }
}

int main(void) {
    int K;
    scanf("%d", &K);

    for (int tc = 1; tc <= K; ++tc) {
        int n, m;
        scanf("%d %d", &n, &m);

        double penalty[100][12];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 12; ++j) {
                scanf("%lf", &penalty[i][j]);
            }
        }

        printf("Data Set %d:\n", tc);

        for (int melody_idx = 0; melody_idx < m; ++melody_idx) {
            char s[205];
            scanf("%204s", s);

            int best_key = -1;
            double best_sum = DBL_MAX;

            for (int key = 0; key < n; ++key) {
                double sum = 0.0;

                for (int i = 0; s[i] != '\0'; ) {
                    int idx = note_index(s[i]);
                    if (s[i + 1] == '#') {
                        ++idx;
                        i += 2;
                    } else {
                        i += 1;
                    }
                    sum += penalty[key][idx];
                }
                if (sum < best_sum) {
                    best_sum = sum;
                    best_key = key + 1;
                }
            }

            printf("%d\n", best_key);
        }

        printf("\n");
    }

    return 0;
}