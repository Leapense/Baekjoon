#include <stdio.h>
#include <string.h>

static int group_of(char c) {
    if ('a' <= c && c <= 'i') return 0;
    if ('j' <= c && c <= 'r') return 1;
    return 2;
}

int main(void) {
    int k1, k2, k3;
    char enc[85], dec[85];
    while (scanf("%d %d %d", &k1, &k2, &k3) == 3) {
        if (k1 == 0 && k2 == 0 && k3 == 0) break;

        scanf("%84s", enc);

        int n = (int)strlen(enc);
        strcpy(dec, enc);

        int pos[3][85];
        int cnt[3] = {0, 0, 0};
        int k[3] = {k1, k2, k3};

        for (int i = 0; i < n; ++i) {
            int g = group_of(enc[i]);
            pos[g][cnt[g]++] = i;
        }

        for (int g = 0; g < 3; ++g) {
            int m = cnt[g];
            if (m == 0) continue;

            int shift = k[g] % m;
            for (int i = 0; i < m; ++i) {
                int to = (i + shift) % m;
                dec[pos[g][to]] = enc[pos[g][i]];
            }
        }

        printf("%s\n", dec);
    }

    return 0;
}