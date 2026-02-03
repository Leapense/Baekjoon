#include <stdio.h>

int main(void) {
    int N, M;
    if (scanf("%d %d", &N, &M) != 2) return 0;

    int A[105];
    for (int i = 1; i <= N; i++) scanf("%d", &A[i]);

    for (int j = 0; j < M; j++) {
        int l, h;
        scanf("%d %d", &l, &h);

        int mx = A[1];
        for (int i = 2; i <= N; i++) {
            if (A[i] > mx) mx = A[i];
        }

        if (A[h] == mx) {

        } else {
            A[l] -= 1;
        }
    }

    for (int i = 1; i <= N; i++) {
        if (i > 1) putchar(' ');
        printf("%d", A[i]);
    }

    putchar('\n');
    return 0;
}