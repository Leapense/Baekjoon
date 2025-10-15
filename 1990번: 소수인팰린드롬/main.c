#include <stdio.h>
#include <math.h>
#include <stdbool.h>

bool is_prime(int n) {
    if (n < 2) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    int limit = (int)sqrt((double)n);
    for (int i = 3; i <= limit; i += 2) {
        if (n % i == 0) return false;
    }
    return true;
}

bool is_palindrome(int n) {
    int rev = 0, tmp = n;
    while (tmp > 0) {
        rev = rev * 10 + tmp % 10;
        tmp /= 10;
    }

    return rev == n;
}

int main(void) {
    int a, b;
    scanf("%d %d", &a, &b);
    for (int n = a; n <= b; n++) {
        if (is_palindrome(n) && is_prime(n)) {
            printf("%d\n", n);
        }
    }
    printf("-1\n");
    return 0;
}