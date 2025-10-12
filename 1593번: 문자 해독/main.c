#include <stdio.h>
#include <stdlib.h>

static inline int idx(char c) {
    return (c >= 'A' && c <= 'Z') ? (c - 'A') : (c - 'a' + 26);
}

int main(void) {
    int g, n;
    if (scanf("%d %d", &g, &n) != 2) return 0;

    char *W = (char*)malloc((size_t)g + 5);
    char *S = (char*)malloc((size_t)n + 5);
    if (!W || !S) return 0;

    scanf("%s", W);
    scanf("%s", S);

    int bal[52] = {0};
    
    for (int i = 0; i < g; ++i) bal[idx(W[i])]--;
    for (int i = 0; i < g; ++i) bal[idx(S[i])]++;

    int diff = 0;
    for (int i = 0; i < 52; ++i) if (bal[i] != 0) ++diff;

    long long ans = (diff == 0) ? 1 : 0;
    for (int i = g; i < n; ++i) {
        int oi = idx(S[i - g]);
        if (bal[oi] == 0) ++diff;
        else if (bal[oi] == 1) --diff;
        --bal[oi];

        int ni = idx(S[i]);
        if (bal[ni] == 0) ++diff;
        else if (bal[ni] == -1) --diff;
        ++bal[ni];

        if (diff == 0) ++ans;
    }

    printf("%lld\n", ans);

    free(W);
    free(S);
    return 0;
}