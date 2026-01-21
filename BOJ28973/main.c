#include <stdio.h>
#include <math.h>

int main(void) {
    long long X, Y;
    if (scanf("%lld %lld", &X, &Y) != 2) return 0;

    (void)Y;
    double x = (double)X * (1.0 - 1.0 / sqrt(2.0));

    printf("%.10f\n", x);

    return 0;
}