#include <stdio.h>

int main(void) {
    long long cur;
    if (scanf("%lld", &cur) != 1) return 0;

    long long x;
    while (scanf("%lld", &x) == 1) {
        if (x < cur) cur += x;
        else {
            printf("%lld\n", cur);
            return 0;
        }
    }

    printf("%lld\n", cur);
    return 0;
}