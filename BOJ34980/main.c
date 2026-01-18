#include <stdio.h>
#include <string.h>

static int count_w(const char *s) {
    int c = 0;
    for (int i = 0; s[i]; ++i) if (s[i] == 'w') ++c;
    return c;
}

int main(void) {
    int N;
    char A[205], B[205];

    if (scanf("%d", &N) != 1) return 0;
    if (scanf("%s", A) != 1) return 0;
    if (scanf("%s", B) != 1) return 0;

    int ca = count_w(A);
    int cb = count_w(B);

    if (cb < ca) {
        puts("Oryang");
    } else if (cb > ca) {
        puts("Manners maketh man");
    } else {
        if (strcmp(A, B) == 0) puts("Good");
        else puts("Its fine");
    }

    return 0;
}
