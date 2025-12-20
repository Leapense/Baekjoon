#include <stdio.h>
#include <string.h>

int main(void) {
    char tok[256];
    int line_len = 0;
    
    while (scanf("%255s", tok) == 1) {
        if (strcmp(tok, "<br>") == 0) {
            putchar('\n');
            line_len = 0;
        } else if (strcmp(tok, "<hr>") == 0) {
            if (line_len != 0) putchar('\n');
            for (int i = 0; i < 80; ++i) putchar('-');
            putchar('\n');
            line_len = 0;
        } else {
            int wlen = (int)strlen(tok);
            if (line_len == 0) {
                fputs(tok, stdout);
                line_len = wlen;
            } else {
                if (line_len + 1 + wlen <= 80) {
                    putchar(' ');
                    fputs(tok, stdout);
                    line_len += 1 + wlen;
                } else {
                    putchar('\n');
                    fputs(tok, stdout);
                    line_len = wlen;
                }
            }
        }
    }
    putchar('\n');
    return 0;
}