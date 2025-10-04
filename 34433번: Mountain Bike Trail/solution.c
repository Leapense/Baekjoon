#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main(void) {
    int N;
    if (scanf("%d", &N) != 1) return 0;

    const int MAXM = 1000000;
    int *need = (int*)calloc(MAXM + 1, sizeof(int));
    if (!need) return 0;
    
    long long min_groups = 0;

    for (int i = 0; i < N; ++i) {
        char tok[32];
        if (scanf("%31s", tok) != 1) return 0;
        int v = 0;
        if (isdigit((unsigned char)tok[0])) {
            int m = 0;
            for (char *p = tok; *p; ++p) m = m * 10 + (*p - '0');
            char back[32];
            scanf("%31s", back);
            v = m;
        } else {
            char me[32];
            scanf("%31s", me);
            v = 0;
        }

        if (need[v] > 0) {
            --need[v];
        } else {
            ++min_groups;
        }
        if (v > 0) ++need[v - 1];
    }

    long long max_groups = N;
    printf("%lld %lld\n", min_groups, max_groups);

    free(need);
    return 0;
}