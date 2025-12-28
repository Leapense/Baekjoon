#include <stdio.h>
#include <string.h>
#include <ctype.h>

enum { ELEM_SZ = 26 * 27 };

static void add_mul(long long dst[ELEM_SZ], const long long src[ELEM_SZ], long long mult) {
    for (int i = 0; i < ELEM_SZ; i++) dst[i] += src[i] * mult;
}

static long long parse_number(const char *s, int *idx) {
    long long v = 0;
    while (isdigit((unsigned char)s[*idx])) {
        v = v * 10 + (s[*idx] - '0');
        (*idx)++;
    }
    return v;
}

static long long parse_opt_number(const char *s, int *idx) {
    if (isdigit((unsigned char)s[*idx])) return parse_number(s, idx);
    return 1;
}

static void parse_sequence(const char *s, int *idx, long long out[ELEM_SZ]);
static void parse_element(const char *s, int *idx, long long out[ELEM_SZ]) {
    memset(out, 0, sizeof(long long) * ELEM_SZ);

    if (s[*idx] == '(') {
        (*idx)++;
        parse_sequence(s, idx, out);
        if (s[*idx] == ')') (*idx)++;
        return;
    }

    char u = s[*idx];
    (*idx)++;
    char l = 0;
    if (islower((unsigned char)s[*idx])) {
        l = s[*idx];
        (*idx)++;
    }

    int first = u - 'A';
    int second = l ? (l - 'a' + 1) : 0;
    int id = first * 27 + second;

    out[id] = 1;
}

static void parse_sequence(const char *s, int *idx, long long out[ELEM_SZ]) {
    memset(out, 0, sizeof(long long) * ELEM_SZ);

    while (s[*idx] && s[*idx] != ')' && s[*idx] != '+') {
        long long elem[ELEM_SZ];
        parse_element(s, idx, elem);

        long long mult = parse_opt_number(s, idx);
        add_mul(out, elem, mult);
    }
}

static void parse_formula(const char *s, long long out[ELEM_SZ]) {
    memset(out, 0, sizeof(long long) * ELEM_SZ);
    int idx = 0;
    while (1) {
        long long term_mult = parse_opt_number(s, &idx);
        long long seq[ELEM_SZ];
        parse_sequence(s, &idx, seq);
        add_mul(out, seq, term_mult);
        if (s[idx] == '+') {
            idx++;
            continue;
        }
        break;
    }
}

static int equal_counts(const long long a[ELEM_SZ], const long long b[ELEM_SZ]) {
    for (int i = 0; i < ELEM_SZ; i++) {
        if (a[i] != b[i]) return 0;
    }
    return 1;
}

int main(void) {
    char left[205];
    if (!fgets(left, sizeof(left), stdin)) return 0;
    left[strcspn(left, "\r\n")] = 0;

    int N;
    if (scanf("%d", &N) != 1) return 0;

    long long L[ELEM_SZ];
    parse_formula(left, L);

    for (int i = 0; i < N; i++) {
        char right[205];
        scanf("%s", right);

        long long R[ELEM_SZ];
        parse_formula(right, R);

        if (equal_counts(L, R)) printf("%s==%s\n", left, right);
        else printf("%s!=%s\n", left, right);
    }

    return 0;
}