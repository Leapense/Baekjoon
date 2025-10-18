#include <stdio.h>
#include <string.h>

enum { ALPHA = 128, MAXN = 1005, MAXST = 2 * 1005 + 10 };

typedef struct {
    int next[ALPHA];
    int link;
    int len;
} State;

State st[MAXST];
int sz, last_;

void sam_init(void) {
    sz = 1;
    last_ = 0;
    for (int a = 0; a < ALPHA; ++a) st[0].next[a] = -1;
    st[0].link = -1;
    st[0].len = 0;
}

void sam_extend(unsigned char c) {
    int cur = sz++;
    for (int a = 0; a < ALPHA; ++a) st[cur].next[a] = -1;
    st[cur].len = st[last_].len + 1;
    int p = last_;
    while (p != -1 && st[p].next[c] == -1) {
        st[p].next[c] = cur;
        p = st[p].link;
    }

    if (p == -1) {
        st[cur].link = 0;
    } else {
        int q = st[p].next[c];
        if (st[p].len + 1 == st[q].len) {
            st[cur].link = q;
        } else {
            int clone = sz++;
            st[clone] = st[q];
            st[clone].len = st[p].len + 1;
            while (p != -1 && st[p].next[c] == q) {
                st[p].next[c] = clone;
                p = st[p].link;
            }
            st[q].link = st[cur].link = clone;
        }
    }

    last_ = cur;
}

int main(void) {
    char S[MAXN], P[MAXN];
    if (scanf("%1000s", S) != 1) return 0;
    scanf("%1000s", P);

    sam_init();
    int nS = (int)strlen(S);
    for (int i = 0; i < nS; ++i) sam_extend((unsigned char)S[i]);

    int n = (int)strlen(P);
    static int endLen[MAXN], B[MAXN], L[MAXN];
    int v = 0, l = 0;
    for (int i = 0; i < n; ++i) {
        unsigned char c = (unsigned char)P[i];
        if (st[v].next[c] != -1) {
            v = st[v].next[c];
            ++l;
        } else {
            while (v != -1 && st[v].next[c] == -1) {
                v = st[v].link;
                if (v == -1) break;
                l = st[v].len;
            }
            if (v == -1) { v = 0; l = 0; }
            else { v = st[v].next[c]; ++l; }
        }
        endLen[i] = l;
    }

    for (int r = 0; r < n; ++r) B[r] = endLen[r] - r;

    static int dq[MAXN];
    int head = 0, tail = 0;
    int j = 0;
    for (int i = 0; i < n; ++i) {
        while (j < n) {
            while (head < tail && B[dq[tail - 1]] >= B[j]) --tail;
            dq[tail++] = j;
            if (B[dq[head]] >= 1 - i) {
                ++j;
            } else break;
        }
        L[i] = j - i;
        if (head < tail && dq[head] == i) ++head;
    }

    const int INF = 1e9;
    static int dp[MAXN];
    for (int i = 0; i <= n; ++i) dp[i] = INF;
    dp[n] = 0;
    for (int i = n - 1; i >= 0; --i) {
        int best = INF;
        for (int t = 1; t <= L[i]; ++t) {
            if (dp[i + t] < best) best = dp[i + t];
        }
        dp[i] = 1 + best;
    }
    printf("%d\n", dp[0]);
    return 0;
}