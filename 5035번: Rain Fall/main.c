#include <stdio.h>
#include <math.h>

double calculate_rain_from_quadratic(double H_end, double K, double L, double T1) {
    double term_b = H_end + (K * T1);
    double term_c = K * L * T1;
    double discriminant = (term_b * term_b) - (4.0 * term_c);
    if (discriminant < 0) return H_end;
    return (term_b + sqrt(discriminant)) / 2.0;
}

int main() {
    double L, K, T1, T2, H;
    if (scanf("%lf %lf %lf %lf %lf", &L, &K, &T1, &T2, &H) != 5) {
        return 0;
    }

    double f_min, f_max;
    if (H < L) {
        f_min = H;
        f_max = H;
    } else if (H > L) {
        double h_end_of_rain = H + (K * T2);
        double val = calculate_rain_from_quadratic(h_end_of_rain, K, L, T1);
        f_min = val;
        f_max = val;
    } else {
        f_min = H;
        double h_end_of_rain = H + (K * T2);
        f_max = calculate_rain_from_quadratic(h_end_of_rain, K, L, T1);
    }

    printf("%.6f %.6f\n", f_min, f_max);

    return 0;
}