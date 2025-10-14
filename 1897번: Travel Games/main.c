#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define MAXW 1100
#define MAXLEN 81

static int cmp_strptr(const void *a, const void *b) {
    const char *sa = *(const char* const*)a;
    const char *sb = *(const char* const*)b;
    return strcmp(sa, sb);
}

struct Ctx {
    char **words;
    int *lens;
} g;

static int cmp_len_then_lex(const void *pa, const void *pb) {
    const int a = *(const int*)pa;
    const int b = *(const int*)pb;
    if (g.lens[a] != g.lens[b]) return g.lens[a] - g.lens[b];
    return strcmp(g.words[a], g.words[b]);
}

static int find_index(char **arr, int n, const char *key) {
    char *keyptr = (char *)key;
    char **res = (char**)bsearch(&keyptr, arr, (size_t)n, sizeof(char *), cmp_strptr);
    if (!res) return -1;
    return (int)(res - arr);
}

int main(void) {
    int D;
    char seed[MAXLEN];
    if (scanf("%d %80s", &D, seed) != 2) return 0;

    char *raw[MAXW];
    int rawn = 0;

    for (int i = 0; i < D; ++i) {
        char buf[MAXLEN];
        if (scanf("%80s", buf) != 1) return 0;
        size_t len = strlen(buf);
        char *p = (char*)malloc(len + 1);
        strcpy(p, buf);
        raw[rawn++] = p;
    }
    qsort(raw, (size_t)rawn, sizeof(char*), cmp_strptr);

    char *words[MAXW];
    int n = 0;
    for (int i = 0; i < rawn; ++i) {
        if (i == 0 || strcmp(raw[i], raw[i-1]) != 0) {
            words[n++] = raw[i];
        } else {
            free(raw[i]);
        }
    }

    int seedIdx = find_index(words, n, seed);
    if (seedIdx == -1) {
        printf("%s\n", seed);
        for (int i = 0; i < n; ++i) free(words[i]);
        return 0;
    }

    int *lens = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; ++i) lens[i] = (int)strlen(words[i]);

    int *order = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; ++i) order[i] = i;

    g.words = words;
    g.lens = lens;
    qsort(order, (size_t)n, sizeof(int), cmp_len_then_lex);

    int *dp = (int*)malloc(sizeof(int) * n);
    int *parent = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; ++i) {
        dp[i] = INT_MIN;
        parent[i] = -1;
    }
    dp[seedIdx] = 0;
    int bestIdx = seedIdx;
    const int seedLen = (int)strlen(seed);
    char buf[MAXLEN];

    for (int t = 0; t < n; ++t) {
        int i = order[t];
        if (lens[i] < seedLen) continue;
        if (i == seedIdx) continue;

        const char *w = words[i];
        int wlen = lens[i];
        int bestPred = -1;
        int bestVal = INT_MIN;

        for (int pos = 0; pos < wlen; ++pos) {
            if (pos > 0) memcpy(buf, w, (size_t)pos);
            memcpy(buf + pos, w + pos + 1, (size_t)(wlen - pos));
            buf[wlen - 1] = '\0';
            int j = find_index(words, n, buf);
            if (j != -1 && dp[j] != INT_MIN) {
                int cand = dp[j] + 1;
                if (cand > bestVal) {bestVal = cand; bestPred = j; }
            }
        }

        if (bestPred != -1) {
            dp[i] = bestVal;
            parent[i] = bestPred;
            if (lens[i] > lens[bestIdx]) bestIdx = i;
        }
    }
    printf("%s\n", words[bestIdx]);
    for (int i = 0; i < n; ++i) free(words[i]);
    free(lens);
    free(order);
    free(dp);
    free(parent);
    return 0;
}