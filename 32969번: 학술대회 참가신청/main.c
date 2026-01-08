#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(void) {
    char s[256];
    if (!fgets(s, sizeof(s), stdin)) return 0;

    size_t len = strlen(s);
    if (len > 0 && s[len - 1] == '\n') s[len - 1] = '\0';

    for (size_t i = 0; s[i]; i++) {
        s[i] = (char)tolower((unsigned char)s[i]);
    }

    const char *digital[] = {"social", "history", "language", "literacy"};
    const char *public_bd[] = {"bigdata", "public", "society"};

    for (size_t i = 0; i < sizeof(digital) / sizeof(digital[0]); i++) {
        if (strstr(s, digital[i]) != NULL) {
            puts("digital humanities");
            return 0;
        }
    }

    for (size_t i = 0; i < sizeof(public_bd) / sizeof(public_bd[0]); i++) {
        if (strstr(s, public_bd[i]) != NULL) {
            puts("public bigdata");
            return 0;
        }
    }

    return 0;
}
