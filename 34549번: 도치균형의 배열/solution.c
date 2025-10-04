#include <stdio.h>

int main(void) {
    int M;
    if (scanf("%d", &M) != 1) return 0;

    int first = 1;
    for (int i = 1; i <= M; ++i) {
        if (!first) printf(" ");
        printf("%d", i);
        first = 0;
    }
    for (int i = M; i >= 1; --i) {
        printf(" %d", i);
    }
    printf("\n");
    return 0;
}