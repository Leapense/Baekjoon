#include <stdio.h>

int main(void) {
    int x, y, z;
    if (scanf("%d %d %d", &x, &y, &z) != 3) return 0;

    int n = x + y + 2 * z;

    char grade;
    if (n >= 360) grade = 'A';
    else if (n >= 320) grade = 'B';
    else if (n >= 280) grade = 'C';
    else if (n >= 240) grade = 'D';
    else grade = 'F';

    printf("%c\n", grade);
    return 0;
}