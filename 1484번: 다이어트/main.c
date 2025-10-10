#include <stdio.h>

int main(void) {
    long long G;
    if (scanf("%lld", &G) != 1) return 0;

    int found = 0;
    long long R = 1, C = 1;

    while (1) {
        long long diff = C * C - R * R;
        if (diff == G) {
            printf("%lld\n", C);
            found = 1;
            ++R;
        } else if (diff > G) {
            ++R;
        } else {
            ++C;
        }

        if (2 * C - 1 > G) break;
    }

    if (!found) puts("-1");
    return 0;
}