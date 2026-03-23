#include <stdio.h>
#include <stdbool.h>

#define MAXN 200000

static bool is_valid(const char *s, int n) {
    int i = 0;

    while (i < n) {
        if (s[i] != 'M') return false;
        ++i;

        if (i + 1 >= n || s[i] != 'I' || s[i + 1] != 'T') return false;
        i += 2;

        while (i < n && s[i] == 'I') {
            if (i + 1 >= n || s[i + 1] != 'T') return false;
            i += 2;
        }
        if (i < n && s[i] != 'M') return false;
    }
    return true;
}

int main(void) {
    int T;
    scanf("%d", &T);

    while (T--) {
        int len;
        static char s[MAXN + 5];

        scanf("%d", &len);
        scanf("%s", s);

        puts(is_valid(s, len) ? "YES" : "NO");
    }

    return 0;
}