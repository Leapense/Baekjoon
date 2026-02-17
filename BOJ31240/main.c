#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(void) {
    static char s[200005];
    if (scanf("%200000s", s) != 1) return 0;

    size_t N = strlen(s);
    if (N < 2) { puts("0"); return 0; }
    if (s[0] == '0') { puts("0"); return 0; }

    size_t limit = N / 2;
    long long ans = 0;

    for (size_t k = 1; k <= limit; k++) {
        if (s[k] == '0') continue;

        if (2 * k < N) {
            ans++;
        } else {
            int cmp = memcmp(s, s + k, k);
            if (cmp <= 0) ans++;
        }
    }

    printf("%lld\n", ans);
    return 0;
}