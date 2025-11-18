#include <stdio.h>
#include <math.h>

int main(void) {
    int N;
    while (scanf("%d", &N) == 1 && N >= 3) {
        double x[1005], y[1005];

        for (int i = 0; i < N; ++i) {
            if (scanf("%lf %lf", &x[i], &y[i]) != 2) {
                return 0;
            }
        }

        double V;
        if (scanf("%lf", &V) != 1) {
            return 0;
        }

        double area2 = 0.0;
        for (int i = 0; i < N; ++i) {
            int j = (i + 1) % N;
            area2 += x[i] * y[j] - x[j] * y[i];
        }

        double area = fabs(area2) * 0.5;
        double length = V / area;

        printf("BAR LENGTH: %.2f\n", length);
    }

    return 0;
}