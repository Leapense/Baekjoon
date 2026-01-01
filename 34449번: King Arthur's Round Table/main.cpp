#include <iostream>

int main() {
    long double d, w;
    long long n;
    
    if (!(std::cin >> d)) return 0;
    std::cin >> w >> n;
    
    constexpr long double PI = 3.14159L;
    constexpr long double EPS = 1e-12L;
    
    long double circumference = PI * d;
    long double required = w * static_cast<long double>(n);
    std::cout << ((circumference + EPS >= required) ? "YES" : "NO") << "\n";
    return 0;
}