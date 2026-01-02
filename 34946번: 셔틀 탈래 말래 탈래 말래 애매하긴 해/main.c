#include <stdio.h>
#include <stdbool.h>

int main(void) {
    int A, B, C, D;
    if (scanf("%d %d %d %d", &A, &B, &C, &D) != 4) return 0;

    bool shuttle_ok = (A + B <= D);
    bool walk_ok = (C <= D);

    if (shuttle_ok && walk_ok) {
        puts("~.~");
    } else if (!shuttle_ok && !walk_ok) {
        puts("T.T");
    } else if (shuttle_ok) {
        puts("Shuttle");
    } else {
        puts("Walk");
    }

    return 0;
}