#include <stdio.h>
#include <string.h>

static const char* phase_of_day(int d) {
    if (d == 0) return "New";
    if (1 <= d && d <= 4) return "Crescent";
    if (5 <= d && d <= 8) return "Quarter";
    if (9 <= d && d <= 13) return "Gibbous";
    if (d == 14) return "Full";
    if (15 <= d && d <= 19) return "Gibbous";
    if (20 <= d && d <= 22) return "Quarter";
    return "Crescent";
}

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return 0;

    char obs[101][16];
    for (int i = 0; i < n; i++) {
        scanf("%15s", obs[i]);
    }

    for (int k = 1; k <= 28; k++) {
        int ok = 1;
        for (int i = 1; i <= n; i++) {
            int d = (i * k) % 28;
            if (strcmp(phase_of_day(d), obs[i - 1]) != 0) {
                ok = 0;
                break;
            }
        }
        if (ok) {
            printf("%d", k);
            return 0;
        }
    }

    return 0;
}