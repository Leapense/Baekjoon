#include <stdio.h>

int main(void) {
    long long M, V;
    long long n;
    long long K;
    
    if (scanf("%lld %lld", &M, &V) != 2) return 0;
    if (scanf("%lld", &n) != 1) return 0;
    if (scanf("%lld", &K) != 1) return 0;
    
    long long total = 40 + 2 * n;
    printf("%lld\n", total);
    
    return 0;
}