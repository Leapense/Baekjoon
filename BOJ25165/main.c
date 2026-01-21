#include <stdio.h>
#include <stdbool.h>

int main(void) {
    int N, M;
    int Ac, D;
    int Sr, Sc;

    if (scanf("%d %d", &N, &M) != 2) return 0;
    if (scanf("%d %d", &Ac, &D) != 2) return 0;
    if (scanf("%d %d", &Sr, &Sc) != 2) return 0;

    if (Sr != N) {
        puts("NO...");
        return 0;
    }

    bool startAtM = (D == ((N % 2 == 0) ? 1 : 0));

    if (startAtM) puts("YES!");
    else puts("NO...");

    return 0;
}