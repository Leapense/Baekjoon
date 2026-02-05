#include <stdio.h>
#include <string.h>

static int outcome(const char *a, const char *b) {
    if (strcmp(a, b) == 0) return 0;
    if (strcmp(a, "rock") == 0) return (strcmp(b, "scissors") == 0) ? 1 : -1;
    if (strcmp(a, "paper") == 0) return (strcmp(b, "rock") == 0) ? 1 : -1;
    return (strcmp(b, "paper") == 0) ? 1 : -1;
}

int main(void) {
    int n, k;
    int firstCase = 1;

    while (scanf("%d", &n) == 1) {
        if (n == 0) break;
        if (scanf("%d", &k) != 1) break;

        int wins[101] = {0};
        int losses[101] = {0};

        int games = k * n * (n - 1) / 2;
        for (int i = 0; i < games; i++) {
            int p1, p2;
            char m1[10], m2[10];
            scanf("%d %9s %d %9s", &p1, m1, &p2, m2);

            int r = outcome(m1, m2);
            if (r == 1) {
                wins[p1]++; losses[p2]++;
            } else if (r == -1) {
                wins[p2]++; losses[p1]++;
            }
        }

        if (!firstCase) putchar('\n');
        firstCase = 0;

        for (int p = 1; p <= n; p++) {
            int w = wins[p], l = losses[p];
            if (w + l == 0) {
                puts("-");
            } else {
                double avg = (double)w / (double)(w + l);
                printf("%.3f\n", avg);
            }
        }
    }
    return 0;
}