#include <stdio.h>

int main(void) {
    int maxPos[10] = {0};
    for (int pos = 1; pos <= 100; pos++) {
        int x;
        if (scanf("%d", &x) != 1) return 0;
        int d = x % 10;
        if (pos > maxPos[d]) maxPos[d] = pos;
    }

    int bestDigit = 0;
    for (int d = 1; d <= 9; d++) {
        if (maxPos[d] > maxPos[bestDigit]) bestDigit = d;
    }

    int answerPlayer = (bestDigit == 0) ? 10 : bestDigit;
    printf("%d", answerPlayer);
    return 0;
}