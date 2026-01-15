#include <stdio.h>

int main(void) {
    int A, B;
    if (scanf("%d %d", &A, &B) != 2) return 0;
    int printed = 0;

    if (A != 0) {
        if (A == 1) {
            printf("x");
        } else if (A == -1) {
            printf("-x");
        } else {
            printf("%dx", A);
        }

        printed = 1;
    }

    if (B != 0) {
        if (printed) {
            if (B > 0) printf("+%d", B);
            else printf("%d", B);
        } else {
            printf("%d", B);
        }
    }

    if (!printed && B == 0) {
        printf("0");
    }

    return 0;
}