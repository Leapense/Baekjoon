#include <stdio.h>
#include <string.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    int c;
    while ((c = getchar()) != '\n' && c != EOF) {}

    char s[2005];
    if (!fgets(s, sizeof(s), stdin)) return 0;

    size_t len = strlen(s);
    if (len && s[len - 1] == '\n') s[len - 1] = '\0';

    const char *qwerty = "qwertyuiopasdfghjklzxcvbnm";

    for (size_t i = 0; s[i]; ++i) {
        if (s[i] == ' ') putchar(' ');
        else putchar(qwerty[s[i] - 'a']);
    }
    return 0;
}