#include <iostream>
#include <algorithm>
#include <cmath>
#include <iomanip>
#include <limits>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int n;
    if (!(std::cin >> n)) return 0;

    long long minX0 = std::numeric_limits<long long>::max();
    long long maxX0 = std::numeric_limits<long long>::lowest();
    long long maxAbsY = 0;
    int axisCount = 0;

    for (int i = 0; i < n; i++) {
        long long x, y;
        std::cin >> x >> y;

        if (y == 0) {
            axisCount++;
            minX0 = std::min(minX0, x);
            maxX0 = std::max(maxX0, x);
        } else {
            maxAbsY = std::max(maxAbsY, std::abs(y));
        }
    }

    long double area = 0.0L;
    if (axisCount >= 2 && maxAbsY > 0) {
        long long base = maxX0 - minX0;

        area = static_cast<long double>(base) * static_cast<long double>(maxAbsY) / 2.0L;
    }

    std::cout << std::fixed << std::setprecision(15) << area << "\n";
    return 0;
}