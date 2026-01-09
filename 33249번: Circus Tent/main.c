#include <stdio.h>
#include <math.h>

int main(void) {
    long double d, h;
    if (scanf("%Lf %Lf", &d, &h) != 2) return 0;

    const long double pi = acosl(-1.0L);
    long double r = d / 2.0L + 5.0L;
    long double area = pi * r * (2.0L * h + r);

    printf("%.17Lf\n", area);
    return 0;
}
