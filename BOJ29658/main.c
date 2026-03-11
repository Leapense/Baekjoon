#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

static bool allowed83(char c) {
    if ('A' <= c && c <= 'Z') return true;
    if ('0' <= c && c <= '9') return true;
    const char *extra = "'-!#$%&()@^_{}.~";
    return strchr(extra, c) != NULL;
}

static bool valid83(const char *s) {
    int dots = 0;
    int n = (int)strlen(s);

    for (int i = 0; i < n; ++i) {
        if (!allowed83(s[i])) return false;
        if (s[i] == '.') ++dots;
    }
    if(dots > 1) return false;

    const char *p = strchr(s, '.');
    if (p == NULL) return n <= 8;

    int left = (int)(p - s);
    int right = n - left - 1;
    return left <= 8 && right <= 3;
}

int main(void) {
    char s[300];
    if (!fgets(s, sizeof(s), stdin)) return 0;

    size_t n = strlen(s);
    if (n > 0 && s[n - 1] == '\n') s[n - 1] = '\0';

    for (int i = 0; s[i] != '\0'; ++i) {
        s[i] = (char)toupper((unsigned char)s[i]);
    }

    if (valid83(s)) {
        printf("%s", s);
        return 0;
    }

    char t[300];
    int p = 0;

    for (int i = 0; s[i] != '\0'; ++i) {
        char c = s[i];
        if (c == ' ') continue;

        if (c == '"' || c == '/' || c == '\\' || c == '[' || c == ']' || c == ':' || c == ';' || c == '=' || c == ',') {
            t[p++] = '_';
        } else {
            t[p++] = c;
        }
    }

    t[p] = '\0';

    while (p > 0 && t[p - 1] == '.') {
        t[--p] = '\0';
    }

    int lastDot = -1;
    for (int i = 0; t[i] != '\0'; ++i) {
        if (t[i] == '.') lastDot = i;
    }

    if (lastDot != -1) {
        char u[300];
        int q = 0;
        for (int i = 0; t[i] != '\0'; ++i) {
            if (t[i] == '.' && i != lastDot) continue;
            u[q++] = t[i];
        }

        u[q] = '\0';
        strcpy(t, u);
    }

    lastDot = -1;
    for (int i = 0; t[i] != '\0'; ++i) {
        if (t[i] == '.') lastDot = i;
    }

    if (lastDot == -1) {
        char X[7];
        int xl = 0;
        while (t[xl] != '\0' && xl < 6) {
            X[xl] = t[xl];
            ++xl;
        }

        X[xl] = '\0';
        printf("%s~1", X);
    } else {
        char X[7], Y[4];
        int xl = 0, yl = 0;

        for (int i = 0; i < lastDot && xl < 6; ++i) {
            X[xl++] = t[i];
        }

        X[xl] = '\0';

        for (int i = lastDot + 1; t[i] != '\0' && yl < 3; ++i) {
            Y[yl++] = t[i];
        }
        Y[yl] = '\0';
        printf("%s~1.%s", X, Y);
    }

    return 0;
}