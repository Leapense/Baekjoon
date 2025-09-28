#include <stdio.h>

int main(void)
{
    long double W, H, x, y, x1, y1, x2, y2;
    if (scanf("%Lf %Lf", &W, &H) != 2) return 0;
    scanf("%Lf %Lf", &x, &y);
    scanf("%Lf %Lf %Lf %Lf", &x1, &y1, &x2, &y2);

    if (x1 > x2) {
        long double tmp = x1;
        x1 = x2;
        x2 = tmp;
    }

    long double ans = 0.0L;
    if (y < y1) {
        ans = 0.0L;
    } else if (y == 0.0L) {
        if (y1 != 0.0L) {
            ans = 0.0L;
        } else {
            if (x >= x1 && x <= x2) {
                ans = 1.0L;
            } else if (x < x1) {
                long double len = (W - x1 > 0.0L) ? (W - x1) : 0.0L;
                ans = (W == 0.0L) ? 0.0L : len / W;
            } else { // x > x2
                long double len = x2;
                if (len < 0.0L) len = 0.0L;
                if (len > W) len = W;
                ans = (W == 0.0L) ? 0.0L : len / W;
            }
        }
    } else {
        long double alpha = 1.0L - y1 / y;
        long double beta = x * (y1 / y);

        long double L = (x1 - beta) / alpha;
        long double R = (x2 - beta) / alpha;

        if (L > R) {
            long double t = L; L = R; R = t;
        }

        long double left = (L > 0.0L) ? L : 0.0L;
        long double right = (R < W) ? R : W;
        long double len = right - left;
        if (len < 0.0L) len = 0.0L;
        ans = (W == 0.0L) ? 0.0L : len / W;
    }

    printf("%.12Lf\n", ans);
    return 0;
}