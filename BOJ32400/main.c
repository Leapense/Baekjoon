#include <stdio.h>

int main(void) {
    int a[20];
    for (int i = 0; i < 20; i++) scanf("%d", &a[i]);

    int pos = -1;
    for (int i = 0; i < 20; i++) {
        if (a[i] == 20) { pos = i; break; }
    }

    int left = a[(pos + 19) % 20];
    int right = a[(pos + 1) % 20];
    int aliceSum = left + 20 + right;

    int lhs = 2 * aliceSum;
    int rhs = 63;

    if (lhs > rhs) puts("Alice");
    else if (lhs < rhs) puts("Bob");
    else puts("Tie");

    return 0;
}