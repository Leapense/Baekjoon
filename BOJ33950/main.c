#include <stdio.h>
#include <string.h>

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;

    for (int tc = 0; tc < T; tc++) {
        char s[64];
        scanf("%63s", s);

        char out[128];
        int k = 0;
        int n = (int)strlen(s);

        for (int i = 0; i < n; ) {
            if (i + 1 < n && s[i] == 'P' && s[i + 1] == 'O') {
                out[k++] = 'P';
                out[k++] = 'H';
                out[k++] = 'O';
                i += 2;
            } else {
                out[k++] = s[i++];
            }
        }
        out[k] = '\0';
        puts(out);
    }

    return 0;
}
