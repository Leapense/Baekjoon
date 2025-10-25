#include <stdio.h>
#include <string.h>
#include <stdbool.h>

static inline int toI(char c) { return c - 'a'; }
static inline char toC(int x) { return (char)('a' + x); }

int min_period(const int *d, int L) {
    for (int k = 1; k * 2 <= L; ++k) {
        bool ok = true;
        for (int i = k; i < L; i++) {
            if (d[i] != d[i - k]) { ok = false; break; }
        }
        if (ok) return k;
    }

    return 0;
}

int main(void) {
    char C[1005], S[105];
    if (scanf("%1000s", C) != 1) return 0;
    if (scanf("%100s", S) != 1) return 0;

    int N = (int)strlen(C);
    int L = (int)strlen(S);
    int Ci[1005], Si[105];
    for (int i = 0; i < N; i++) Ci[i] = toI(C[i]);
    for (int i = 0; i < L; i++) Si[i] = toI(S[i]);

    int D[105], K[105];
    for (int s = 0; s + L <= N; ++s) {
        for (int i = 0; i < L; i++) {
            int v = Ci[s + i] - Si[i];
            if (v < 0) v += 26;
            D[i] = v;
        }
        int k = min_period(D, L);
        if (k == 0) continue;

        for (int i = 0; i < L; i++) K[i] -= 1;
        bool ok = true;
        for (int i = 0; i < L; i++) {
            int t = (s + i) % k;
            if (K[t] == -1) K[t] = D[i];
            else if (K[t] != D[i]) { ok = false; break; }
        }
        if (!ok) continue;

        char P[1005];
        for (int i = 0; i < N; i++) {
            int v = Ci[i] - K[i % k];
            if (v < 0) v += 26;
            P[i] = toC(v);
        }
        P[N] = '\0';

        if (strstr(P, S) != NULL) {
            puts(P);
            return 0;
        }
    }

    return 0;
}