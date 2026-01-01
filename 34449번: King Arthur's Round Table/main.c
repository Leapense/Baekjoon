#include <stdio.h>

int main(void) {
    long double d, w;
    long long n;
    
    if (scanf("%Lf", &d) != 1) return 0;
    if (scanf("%Lf", &w) != 1) return 0;
    if (scanf("%lld", &n) != 1) return 0;
    
    const long double PI = 3.14159L;
    const long double EPS = 1e-12L;
    
    long double circumference = PI * d;
    long double required = w * (long double)n;
    
    if (circumference + EPS >= required) {
        puts("YES");
    } else {
        puts("NO");
    }
    
    return 0;
}