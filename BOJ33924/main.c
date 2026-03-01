#include <stdio.h>

int main(void) {
    int N, M, K;
    if (scanf("%d %d", &N, &M) != 2) return 0;
    if (scanf("%d", &K) != 1) return 0;

    char S[3005];
    if (scanf("%3000s", S) != 1) return 0;

    int id = (N - 1) * 2 + (M - 1);

    static const int permA[8] = {4, 5, 6, 7, 0, 1, 2, 3};
    static const int permB[8] = {3, 2, 1, 0, 7, 6, 5, 4};
    static const int permC[8] = {7, 6, 5, 4, 3, 2, 1, 0};
    static const int permD[8] = {1, 3, 0, 5, 2, 7, 4, 6};

    for (int i = 0; i < K; i++) {
        char ch = S[i];
        if (ch == 'A') id = permA[id];
        else if (ch == 'B') id = permB[id];
        else if (ch == 'C') id = permC[id];
        else if (ch == 'D') id = permD[id];
    }

    int r = id / 2 + 1;
    int c = id % 2 + 1;
    printf("%d %d", r, c);
    return 0;
}