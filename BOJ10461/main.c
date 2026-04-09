#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int a, b;
    while (scanf("%d %d", &a, &b) == 2) {
        int d = abs(a - b);
        int r = (86400 - a) % d;

        long long M = (1440LL * r + d) / (2LL * d);
        M %= 720;

        int hour = (int)(M / 60);
        int minute = (int)(M % 60);

        if (hour == 0) hour = 12;
        printf("%d %d %02d:%02d\n", a, b, hour, minute);
    }

    return 0;
}