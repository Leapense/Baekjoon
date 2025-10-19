//  쉬운 매췽 - C23
#include <stdio.h>
#include <stdlib.h>

static int* prefixSums(const int* t, int n) {
    int* S = (int*)malloc((n+1)*sizeof(int));
    S[0] = 0;
    for (int i = 1; i <= n; ++i) S[i] = S[i-1] + t[i-1];
    return S;
}
static int* cum(const int* p, int m) {
    int* c = (int*)malloc(m*sizeof(int));
    int s = 0;
    for (int i = 0; i < m; ++i) { s += p[i]; c[i] = s; }
    return c;
}
static int* getEndIndicesForPattern(const int* S, int N, const int* cumP, int m, const int* sum2idx, int* outSz) {
    int total = S[N];
    int* ends = (int*)malloc(N*sizeof(int));
    int sz = 0;
    for (int k = 1; k <= N; ++k) {
        if (m && total - S[k-1] < cumP[m-1]) break;
        int idx = k-1;
        int ok = 1;
        for (int i = 0; i < m; ++i) {
            int target = S[k-1] + cumP[i];
            if (target > total) { ok = 0; break; }
            int pos = sum2idx[target];
            if (pos == -1 || pos <= idx) { ok = 0; break; }
            idx = pos;
        }
        if (ok) ends[sz++] = idx;
    }
    *outSz = sz;
    return ends;
}
static int* getStartPrefixIndices(const int* S, int N, const int* cumP, int m, const int* sum2idx, int* outSz) {
    int total = S[N];
    int* starts = (int*)malloc(N*sizeof(int));
    int sz = 0;
    for (int s = 1; s <= N; ++s) {
        if (m && total - S[s-1] < cumP[m-1]) break;
        int idx = s-1;
        int ok = 1;
        for (int i = 0; i < m; ++i) {
            int target = S[s-1] + cumP[i];
            if (target > total) { ok = 0; break; }
            int pos = sum2idx[target];
            if (pos == -1 || pos <= idx) { ok = 0; break; }
            idx = pos;
        }
        if (ok) starts[sz++] = s-1;
    }
    *outSz = sz;
    return starts;
}

int main(void) {
    int TC;
    if (scanf("%d", &TC) != 1) return 0;
    while (TC--) {
        int N; 
        if (scanf("%d", &N) != 1) return 0;
        int* T = (int*)malloc(N*sizeof(int));
        for (int i = 0; i < N; ++i) scanf("%d", &T[i]);

        int M1; scanf("%d", &M1);
        int* P1 = (int*)malloc(M1*sizeof(int));
        for (int i = 0; i < M1; ++i) scanf("%d", &P1[i]);

        int M2; scanf("%d", &M2);
        int* P2 = (int*)malloc(M2*sizeof(int));
        for (int i = 0; i < M2; ++i) scanf("%d", &P2[i]);

        int* S = prefixSums(T, N);
        int total = S[N];

        int* sum2idx = (int*)malloc((total+1)*sizeof(int));
        for (int v = 0; v <= total; ++v) sum2idx[v] = -1;
        for (int i = 0; i <= N; ++i) sum2idx[S[i]] = i;

        int *C1 = NULL, *C2 = NULL;
        if (M1) C1 = cum(P1, M1);
        if (M2) C2 = cum(P2, M2);

        int rSz = 0, jSz = 0;
        int* R = (M1 ? getEndIndicesForPattern(S, N, C1, M1, sum2idx, &rSz) : NULL);
        int* J = (M2 ? getStartPrefixIndices(S, N, C2, M2, sum2idx, &jSz) : NULL);

        int ans1 = rSz;
        int ans2 = jSz;

        int bestN = 1, bestCnt = 0;
        if (rSz > 0 && jSz > 0) {
            int* freq = (int*)calloc(total+1, sizeof(int));
            for (int a = 0; a < rSz; ++a) {
                int r = R[a];
                for (int b = 0; b < jSz; ++b) {
                    int j = J[b];
                    if (j <= r) continue;
                    int d = S[j] - S[r];
                    if (d >= 1 && d <= total) ++freq[d];
                }
            }
            for (int d = 1; d <= total; ++d) {
                if (freq[d] > bestCnt) { bestCnt = freq[d]; bestN = d; }
            }
            if (bestCnt == 0) bestN = 1;
            free(freq);
        }

        printf("%d %d %d %d\n", ans1, ans2, bestN, bestCnt);

        free(T); free(P1); free(P2);
        free(S); free(sum2idx);
        if (C1) free(C1);
        if (C2) free(C2);
        if (R) free(R);
        if (J) free(J);
    }
    return 0;
}