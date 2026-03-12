#include <stdio.h>

static int isHappy(char c) {
    return c == 'H' || c == 'A' || c == 'P' || c == 'Y';
}

static int isSad(char c) {
    return c == 'S' || c == 'A' || c == 'D';
}

int main(void) {
    char message[2005];
    if (fgets(message, sizeof(message), stdin) == NULL) {
        return 0;
    }

    int PH = 0, PG = 0;

    for (int i = 0; message[i] != '\0'; ++i) {
        char c = message[i];
        if (isHappy(c)) ++PH;
        if (isSad(c)) ++PG;
    }

    double answer;
    if (PH == 0 && PG == 0) {
        answer = 50.0;
    } else {
        answer = 100.0 * (double)PH / (PH + PG);
    }

    printf("%.2f", answer);
    return 0;
}