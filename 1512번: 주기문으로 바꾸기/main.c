#include <stdio.h>
#include <string.h>
#include <limits.h>

static inline int idx(char c) {
    switch(c) {
        case 'A': return 0;
        case 'C': return 1;
        case 'G': return 2;
        case 'T': return 3;
    }
    return 0;
}

int main(void) {
    int M;
    if (scanf("%d", &M) != 1) return 0;
    char S[4005];
    if (scanf("%4000s", S) != 1) return 0;

    int L = (int)strlen(S);
    if (M > L) M = L;
    
    int answer = INT_MAX;

    for (int P = 1; P <= M; ++P) {
        int changes = 0;
        for (int r = 0; r < P; ++r) {
            int cnt[4] = {0,0,0,0};
            int group_size = 0;
            for (int j = r; j < L; j += P) {
                cnt[idx(S[j])]++;
                group_size++;
            }
            int best = cnt[0];
            if (cnt[1] > best) best = cnt[1];
            if (cnt[2] > best) best = cnt[2];
            if (cnt[3] > best) best = cnt[3];
            changes += (group_size - best);
        }
        if (changes < answer) answer = changes;
    }
    printf("%d\n", answer);
    return 0;
}