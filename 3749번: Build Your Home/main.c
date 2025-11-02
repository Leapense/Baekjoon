#include <stdio.h>
#include <math.h>

int main(void)
{
    int k;
    while (1)
    {
        if (scanf("%d", &k) != 1) return 0;
        if (k == 0) break;
        long double x0 = 0.0L, y0 = 0.0L;
        long double x_prev = 0.0L, y_prev = 0.0L;
        long double sum = 0.0L;

        for (int i = 0; i < k; ++i) {
            long double x, y;
            if (scanf("%Lf %Lf", &x, &y) != 2) {
                return 0;
            }

            if (i == 0) {
                x0 = x;
                y0 = y;
            } else {
                sum += x_prev * y - x * y_prev;
            }
            x_prev = x;
            y_prev = y;
        }

        sum += x_prev * y0 - x0 * y_prev;
        long double area = fabsl(sum) * 0.5L;
        long long ans = llroundl(area);

        printf("%lld\n", ans);
    }

    return 0;
}