#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAXLEN 100005

int main(void) {
    int n;
    scanf("%d", &n);
    while (n--) {
        int k;
        char s[MAXLEN];
        scanf("%d %s", &k, s);

        int m = (int) strlen(s);
        int L = m - k;

        if (L == 1) {
            bool hasZero = false;
            for (int i = 0; i < m; ++i) {
                if (s[i] == '0') {
                    hasZero = true;
                    break;
                }
            }

            if (hasZero) {
                printf("0\n");
            } else {
                printf("-1\n");
            }

            continue;
        }

        if (L == 2) {
            printf("-1\n");
            continue;
        }

        int zeroCount = 0;
        for (int i = L - 2; i < m; ++i) {
            if (s[i] == '0') {
                ++zeroCount;
            }
        }

        if (zeroCount >= 2) {
            for (int i = 0; i < L - 2; ++i) {
                putchar(s[i]);
            }

            printf("00\n");
        } else {
            printf("-1\n");
        }
    }

    return 0;
}