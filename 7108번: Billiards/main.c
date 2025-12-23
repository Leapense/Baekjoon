#include <stdio.h>
#include <math.h>

static long double reflect_pos(long double p, long double L) {
    long double period = 2.0L * L;
    long double r = fmodl(p, period);
    if (r < 0) r += period;
    if (r > L) r = period - r;
    if (fabsl(r) < 1e-15L) r = 0;
    return r;
}

int main(void) {
    int k;
    long long n;
    if (scanf("%d %lld", &k, &n) != 2) return 0;

    const long double W = 47.0L, H = 73.0L;
    const long double x0 = 34.0L, y0 = 29.0L;

    long double dx = x0 - W;
    long double dy = y0 - (long double)k;

    long double len = sqrtl(dx * dx + dy * dy);
    long double t = (len == 0.0L ? 0.0L : (long double)n / len);
    
    long double xu = x0 + dx * t;
    long double yu = y0 + dy * t;

    long double x = reflect_pos(xu, W);
    long double y = reflect_pos(yu, H);

    long double BR = W - x;
    long double BD = y;

    if (fabsl(BR) < 1e-15L) BR = 0;
    if (fabsl(BD) < 1e-15L) BD = 0;

    printf("%.3Lf %.3Lf\n", BR, BD);
    return 0;
}