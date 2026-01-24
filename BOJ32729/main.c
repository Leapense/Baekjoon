#include <stdio.h>
#include <string.h>

#define MAX_LINE 1000005

static void strip_newline(char *s) {
    size_t n = strlen(s);
    if (n && (s[n - 1] == '\n' || s[n - 1] == '\r')) s[n - 1] = '\0';
    n = strlen(s);
    if (n && s[n - 1] == '\r') s[n - 1] = '\0';
}

int main(void) {
    static char letters[MAX_LINE];
    static char word[MAX_LINE];

    if (!fgets(letters, sizeof(letters), stdin)) return 0;
    strip_newline(letters);

    int avail[26] = {0};
    for (char *p = letters; *p; ++p) {
        if ('a' <= *p && *p <= 'z') avail[*p - 'a']++;
    }

    int N;
    if (scanf("%d", &N) != 1) return 0;

    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {}

    for (int i = 0; i < N; ++i) {
        if (!fgets(word, sizeof(word), stdin)) break;
        strip_newline(word);

        int need[26] = {0};
        for (char *p = word; *p; ++p) {
            if ('a' <= *p && *p <= 'z') need[*p - 'a']++;
        }

        int ok = 1;
        for (int c = 0; c < 26; ++c) {
            if (need[c] > avail[c]) { ok = 0; break; }
        }

        if (ok) puts(word);
    }

    return 0;
}