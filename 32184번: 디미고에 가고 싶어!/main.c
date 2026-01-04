#include <stdio.h>

int main(void) {
    int A, B;
    if (scanf("%d %d", &A, &B) != 2) return 0;

    int total = B - A + 1;

    int k_low = (A + 2) / 2;
    int k_high = B / 2;

    int pairs = 0;
    if (k_low <= k_high) pairs = k_high - k_low + 1;

    int ans = total - pairs;
    printf("%d\n", ans);

    return 0;
}