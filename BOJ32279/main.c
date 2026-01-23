#include <stdio.h>

int main(void) {
    int n;
    long long p, q, r, s;
    long long a1;

    if (scanf("%d", &n) != 1) return 0;
    scanf("%lld %lld %lld %lld", &p, &q, &r, &s);
    scanf("%lld", &a1);

    long long a[21] = {0};
    a[1] = a1;
    
    long long sum = a[1];
    for (int i = 2; i <= n; i++) {
        int parent = i / 2;
        if (i % 2 == 0) a[i] = p * a[parent] + q;
        else            a[i] = r * a[parent] + s;
        sum += a[i];
    }
    printf("%lld\n", sum);
    return 0;
}