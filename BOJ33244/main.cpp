#include <iostream>
#include <numeric>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int n;
    if (!(std::cin >> n)) return 0;

    long long current_lcm = 1;

    for (int k = 0; k < n; k++) {
        long long val;
        std::cin >> val;

        long long common = std::gcd(current_lcm, val);

        current_lcm = (current_lcm / common) * val;
    }

    std::cout << current_lcm << "\n";
    return 0;
}