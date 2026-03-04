#include <stdio.h>

int main(void)
{
    long long max_first14;
    long long current;

    for (int i = 0; i < 14; i++) {
        if (scanf("%lld", &current) != 1) return 0;
        if (i == 0 || current > max_first14) {
            max_first14 = current;
        }
    }

    long long last_element;
    if (scanf("%lld", &last_element) != 1) return 0;

    long long ans;
    if (last_element > max_first14) {
        ans = last_element;
    } else {
        ans = max_first14 + 1;
    }

    printf("%lld", ans);
    return 0;
}