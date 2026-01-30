#include <stdio.h>

int main(void) {
    long long n;
    if (scanf("%lld", &n) != 1) return 0;

    long long tmp = n;
    int d = 0;
    while (tmp > 0) {
        d++;
        tmp /= 10;
    }

    long long p10 = 1;
    for (int i = 0; i < d; i++) p10 *= 10;
    long long limit = p10 - 1;

    int cnt = 0;
    while (n * 2 <= limit) {
        n *= 2;
        cnt++;
    }

    printf("%d\n", cnt);
    return 0;
}