#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>

static inline bool parse_upto9(const char* s, int n, int pos, int len, int* out) {
    if (len <= 0 || pos < 0 || pos + len > n) return false;
    if (s[pos] == '0' && len > 1) return false;
    long long v = 0;
    for (int i = 0; i < len; ++i) {
        char c = s[pos + i];
        if (c < '0' || c > '9') return false;
        v = v * 10 + (c - '0');
        if (v >= 1000000000LL) return false;
    }
    if (v == 0) return false;

    *out = (int) v;
    return true;
}

static inline int to_string_len(int x, char buf[16]) {
    int i = 0;
    int y = x;
    char tmp[16];
    do {
        tmp[i++] = (char)('0' + (y % 10));
        y /= 10;
    } while (y > 0);
    for (int k = 0; k < i; ++k) buf[k] = tmp[i - 1 - k];
    buf[i] = '\0';
    return i;
}

int main(void) {
    char S[2400];
    if (scanf("%2399s", S) != 1) return 0;
    int L = (int)strlen(S);
    int bestF = INT_MAX;
    for (int l1 = 1; l1 <= 9 && l1 <= L - 2; ++l1) {
        for (int l2 = 1; l2 <= 9 && l1 + l2 <= L - 1; ++l2) {
            int a1, a2;
            if (!parse_upto9(S, L, 0, l1, &a1)) continue;
            if (!parse_upto9(S, L, l1, l2, &a2)) continue;
            if (a2 <= a1) continue;
            int d = a2 - a1;
            int pos = l1 + l2;
            int t = a2;

            while (1) {
                int remLen = L - pos;
                if (remLen > 0 && remLen <= 9 && S[pos] != '0') {
                    int an;
                    if (parse_upto9(S, L, pos, remLen, &an)) {
                        if (an % t == 0) {
                            int f = an / t;
                            if (f >= 2 && f < bestF) bestF = f;
                        }
                    }
                }

                long long nextv = (long long)t + (long long)d;
                if (nextv >= 1000000000LL) break;

                char buf[16];
                int nslen = to_string_len((int)nextv, buf);
                if (pos + nslen > L) break;
                if (strncmp(S + pos, buf, nslen) != 0) break;
                pos += nslen;
                t = (int)nextv;
                if (pos >= L) break;
            }
        }
    }

    if (bestF == INT_MAX) printf("0\n");
    else printf("%d\n", bestF);
    return 0;
}