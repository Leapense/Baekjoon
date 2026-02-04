#include <stdio.h>

int main(void) {
    double n, p;
    if (scanf("%lf %lf", &n, &p) != 2) return 0;

    double loss = n * p * 55.0 / 1000.0;
    printf("%.10f\n", loss);
    return 0;
}