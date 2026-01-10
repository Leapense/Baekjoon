#include <stdio.h>
#include <stdbool.h>

int main(void) {
    char S[101];
    char T[10001];

    if (scanf("%100s %10000s", S, T) != 2) return 0;

    bool present[26] = {false};

    for (int i = 0; S[i] != '\0'; i++) {
        present[S[i] - 'a'] = true;
    }
    for (int i = 0; T[i] != '\0'; i++) {
        if (!present[T[i] - 'a']) {
            putchar(T[i]);
        }
    }

    putchar('\n');

    return 0;
}
