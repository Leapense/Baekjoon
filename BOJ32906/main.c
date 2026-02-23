#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {
    int n;
    if (scanf("%d", &n) != 1) return EXIT_FAILURE;

    long long minX0 = 0, maxX0 = 0;
    bool firstAxis = true;

    int axisCount = 0;
    long long maxAbsY = 0;

    for (int i = 0; i < n; i++) {
        long long x, y;
        if (scanf("%lld %lld", &x, &y) != 2) {
            fprintf(stderr, "Invalid input format.\n");
            return EXIT_FAILURE;
        }

        if (y == 0) {
            axisCount++;
            if (firstAxis) {
                minX0 = maxX0 = x;
                firstAxis = false;
            } else {
                if (x < minX0) minX0 = x;
                if (x > maxX0) maxX0 = x;
            }
        } else {
            long long ay = llabs(y);
            if (ay > maxAbsY) maxAbsY = ay;
        }
    }

    long double area = 0.0L;

    if (axisCount >= 2 && maxX0 != minX0 && maxAbsY > 0) {
        long long base = maxX0 - minX0;
        area = ((long double)base * (long double)maxAbsY) / 2.0L;
    }

    printf("%.15Lf\n", area);
    return EXIT_SUCCESS;
}