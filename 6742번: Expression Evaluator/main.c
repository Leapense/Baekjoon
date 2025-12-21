#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAXLEN 10005

typedef struct {
    int idx;
    int sign;
    int pre;
    int post;
} Term;

static int starts_with2(const char *s, int i, const char *pat) {
    return s[i] && s[i+1] && s[i] == pat[0] && s[i+1] == pat[1];
}

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;

    int ch;
    while ((ch = getchar()) != '\n' && ch != EOF) {}

    char orig[MAXLEN];

    for (int tc = 0; tc < T; tc++) {
        if (!fgets(orig, sizeof(orig), stdin)) break;

        size_t n = strlen(orig);
        if (n > 0 && orig[n-1] == '\n') orig[n-1] = '\0';

        char s[MAXLEN];
        int m = 0;
        for (int i = 0; orig[i]; i++) {
            unsigned char c = (unsigned char)orig[i];
            if (!isspace(c)) s[m++] = (char)c;
        }
        s[m] = '\0';

        int val[26];
        for (int i = 0; i < 26; i++) val[i] = i + 1;

        Term terms[64];
        int termCount = 0;
        int used[26] = {0};

        int i = 0;
        while (s[i]) {
            int sign = +1;
            if (i != 0) {
                if (s[i] == '+') { sign = +1; i++; }
                else if (s[i] == '-') { sign = -1; i++; }
            }

            int pre = 0, post = 0;

            if (starts_with2(s, i, "++")) { pre = +1; i += 2; }
            else if (starts_with2(s, i, "--")) { pre = -1; i += 2; }

            char v = s[i];
            int idx = v - 'a';
            i++;

            if (starts_with2(s, i, "++")) { post = +1; i += 2; }
            else if (starts_with2(s, i, "--")) { post = -1; i += 2; }

            terms[termCount].idx = idx;
            terms[termCount].sign = sign;
            terms[termCount].pre = pre;
            terms[termCount].post = post;
            termCount++;

            used[idx] = 1;
        }

        for (int k = 0; k < termCount; k++) {
            if (terms[k].pre != 0) val[terms[k].idx] += terms[k].pre;
        }

        int result = 0;
        for (int k = 0; k < termCount; k++) {
            result += terms[k].sign * val[terms[k].idx];
        }

        for (int k = 0; k < termCount; k++) {
            if (terms[k].post != 0) val[terms[k].idx] += terms[k].post;
        }

        printf("Expression: %s\n", orig);
        printf("value = %d\n", result);
        for (int idx = 0; idx < 26; idx++) {
            if (used[idx]) {
                printf("%c = %d\n", (char)('a' + idx), val[idx]);
            }
        }
    }

    return 0;
}