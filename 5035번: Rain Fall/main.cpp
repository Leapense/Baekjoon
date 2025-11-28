#include <iostream>
#include <iomanip>
#include <cmath>
#include <algorithm>

double solve_rainfall(double H_end, double K, double L, double T1) {
    double b = -(H_end + (K * T1));
    double c = K * L * T1;

    double discriminant = (b * b) - (4.0 * c);
    double F = (-b + std::sqrt(discriminant)) / 2.0;
    return F;
}

void solve() {
    double L, K, T1, T2, H;
    std::cin.tie(nullptr);
    std::ios_base::sync_with_stdio(false);

    if (!(std::cin >> L >> K >> T1 >> T2 >> H)) return;

    double min_rain = 0.0;
    double max_rain = 0.0;

    if (H < L) {
        min_rain = H;
        max_rain = H;
    } else if (H > L) {
        double H_end = H + (K * T2);
        double calculated_rain = solve_rainfall(H_end, K, L, T1);
        min_rain = calculated_rain;
        max_rain = calculated_rain;
    } else {
        min_rain = H;
        double H_end = H + (K * T2);
        max_rain = solve_rainfall(H_end, K, L, T1);
    }

    std::cout << std::fixed << std::setprecision(6) << min_rain << " " << max_rain << std::endl;
}

int main() {
    solve();
    return 0;
}