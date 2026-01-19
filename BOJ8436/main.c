#include <stdio.h>
#include <string.h>

int main(void) {
    char s[64];
    if (scanf("%63s", s) != 1) return 0;

    long long ans = 1;
    for (size_t i = 0; s[i] != '\0'; i++) {
        char c = s[i];
        if (c == 'T' || c == 'D' || c == 'L' || c == 'F') {
            ans *= 2;
        }
    }

    printf("%lld\n", ans);

    return 0;
}