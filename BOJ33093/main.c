#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAXN 105
#define MAXL 11

static bool contains(char arr[][MAXL], int count, const char *s) {
    for (int i = 0; i < count; i++) {
        if (strcmp(arr[i], s) == 0) return true;
    }

    return false;
}

static void add_unique(char arr[][MAXL], int *count, const char *s) {
    if (!contains(arr, *count, s)) {
        strcpy(arr[*count], s);
        (*count)++;
    }
}

int main(void) {
    int N, M, K;
    if (scanf("%d %d %d", &N, &M, &K) != 3) return 0;

    char teams[MAXN][MAXL];
    char institutions[MAXN][MAXL];

    for (int i = 0; i < N; i++) {
        if (scanf("%10s %10s", teams[i], institutions[i]) != 2) break;
    }

    char seenInstitutions[MAXN][MAXL];
    int seenCount = 0;

    for (int i = 0; i < M && i < N; i++) {
        add_unique(seenInstitutions, &seenCount, institutions[i]);
    }

    char goldenTeams[MAXN][MAXL];
    int goldenCount = 0;

    for (int i = M; i < N && goldenCount < K; i++) {
        if (contains(seenInstitutions, seenCount, institutions[i])) {
            continue;
        }
        add_unique(seenInstitutions, &seenCount, institutions[i]);
        strcpy(goldenTeams[goldenCount++], teams[i]);
    }

    printf("%d\n", goldenCount);
    for (int i = 0; i < goldenCount; i++) {
        printf("%s\n", goldenTeams[i]);
    }

    return 0;
}