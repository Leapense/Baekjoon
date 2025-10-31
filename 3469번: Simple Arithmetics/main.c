#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static char* dupstr(const char* s) {
    size_t n = strlen(s);
    char *r = malloc(n + 1);
    memcpy(r, s, n + 1);
    return r;
}

static char* strip(const char* s) {
    size_t i = 0, n = strlen(s);
    while (i + 1 < n && s[i] == '0') ++i;
    return dupstr(s + i);
}

static char* add_str(const char* a, const char* b) {
    int i = (int)strlen(a) - 1, j = (int)strlen(b) - 1, c = 0;
    int cap = (i > j ? i : j) + 3;
    char *tmp = malloc(cap);
    int k = 0;
    while (i >= 0 || j >= 0 || c) {
        int da = (i >= 0 ? a[i] - '0' : 0);
        int db = (j >= 0 ? b[j] - '0' : 0);
        int s = da + db + c;
        tmp[k++] = (char)('0' + (s % 10));
        c = s / 10;
        --i;
        --j;
    }

    for (int l = 0, r = k - 1; l < r; ++l, --r) {
        char t = tmp[l];
        tmp[l] = tmp[r];
        tmp[r] = t;
    }

    tmp[k] = '\0';
    char* res = strip(tmp);
    free(tmp);
    return res;
}

static char* sub_str(const char* a, const char* b) {
    int i = (int)strlen(a) - 1, j = (int)strlen(b) - 1, borrow = 0;
    int cap = (int)strlen(a) + 1;
    char *tmp = malloc(cap);
    int k = 0;
    while (i >= 0) {
        int da = (a[i] - '0') - borrow;
        int db = (j >= 0 ? b[j] - '0' : 0);
        if (da < db) {
            da += 10;
            borrow = 1;
        } else {
            borrow = 0;
        }
        tmp[k++] = (char)('0' + (da - db));
        --i; --j;
    }

    for (int l = 0, r = k - 1; l < r; ++l, --r) {
        char t = tmp[l];
        tmp[l] = tmp[r];
        tmp[r] = t;
    }

    tmp[k] = '\0';
    char* res = strip(tmp);
    free(tmp);
    return res;
}

static char* mul_digit(const char* a, int d) {
    if (d == 0) return dupstr("0");
    int i = (int)strlen(a) - 1, c = 0;
    int cap = (int)strlen(a) + 3;
    char *tmp = malloc(cap);
    int k = 0;
    while (i >= 0 || c) {
        int da = (i >= 0 ? a[i] - '0' : 0);
        int prod = da * d + c;
        tmp[k++] = (char)('0' + (prod % 10));
        c = prod / 10;
        --i;
    }

    for (int l = 0, r = k - 1; l < r; ++l, --r) {
        char t = tmp[l];
        tmp[l] = tmp[r];
        tmp[r] = t;
    }

    tmp[k] = '\0';
    char* res = strip(tmp);
    free(tmp);
    return res;
}

static char* mul_str(const char* a, const char* b) {
    int n = (int)strlen(a), m = (int)strlen(b);
    int L = n + m;
    int* v = calloc(L, sizeof(int));
    for (int i = n - 1; i >= 0; --i) {
        for (int j = m - 1; j >= 0; --j) {
            v[i + j + 1] += (a[i] - '0') * (b[j] - '0');
        }
    }
    for (int k = L - 1; k > 0; --k) {
        v[k - 1] += v[k] / 10;
        v[k] %= 10;
    }
    int p = 0;
    while (p + 1 < L && v[p] == 0) ++p;
    int len = L - p;
    char* r = malloc(len + 1);
    for (int i = 0; i < len; ++i) {
        r[i] = (char)('0' + v[p + i]);
    }
    r[len] = '\0';
    free(v);
    return r;
}

static void print_repeat(char ch, int cnt) {
    for (int i = 0; i < cnt; ++i) {
        putchar(ch);
    }
}

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;
    while (T--) {
        char buf[1205];
        scanf("%s", buf);
        int pos = -1;
        char op = 0;
        for (int i = 0; buf[i]; ++i) {
            if (buf[i] == '+' || buf[i] == '-' || buf[i] == '*') {
                pos = i;
                op = buf[i];
                break;
            }
        }

        char *A = dupstr(buf); A[pos] = '\0';
        char *B = dupstr(buf + pos + 1);

        char *R = NULL;
        char **parts = NULL;
        int parts_n = 0;

        if (op == '+') R = add_str(A, B);
        else if (op == '-') R = sub_str(A, B);
        else {
            R = mul_str(A, B);
            int m = (int)strlen(B);
            parts = malloc(sizeof(char*) * m);
            parts_n = m;
            for (int k = m - 1, t = 0; k >= 0; --k, ++t) {
                int d = B[k] - '0';
                parts[t] = mul_digit(A, d);
            }
        }

        int lenA = (int)strlen(A);
        int lenBop = (int)strlen(B) + 1;
        int lenR = (int)strlen(R);

        if (op == '+' || op == '-') {
            int W = lenA;
            if (W < lenBop) W = lenBop;
            if (W < lenR) W = lenR;
            int dash1 = (lenBop > lenR ? lenBop : lenR);

            print_repeat(' ', W - lenA); fputs(A, stdout); putchar('\n');
            print_repeat(' ', W - lenBop); putchar(op); fputs(B, stdout); putchar('\n');
            print_repeat(' ', W - dash1); print_repeat('-', dash1); putchar('\n');
            print_repeat(' ', W - lenR); fputs(R, stdout); putchar('\n'); putchar('\n');
        } else {
            int m = (int)strlen(B);
            if (m == 1) {
                int dash1 = ((lenBop > (int)strlen(parts[0])) ? lenBop : (int)strlen(parts[0]));
                int W = lenA;
                if (W < lenBop) W = lenBop;
                if (W < lenR) W = lenR;
                print_repeat(' ', W - lenA); fputs(A, stdout); putchar('\n');
                print_repeat(' ', W - lenBop); putchar(op); fputs(B, stdout); putchar('\n');
                print_repeat(' ', W - dash1); print_repeat('-', dash1); putchar('\n');
                print_repeat(' ', W - lenR); fputs(R, stdout); putchar('\n'); putchar('\n');
            } else {
                int max_part = 0;
                for (int i = 0; i < parts_n; ++i) {
                    int eff = (int)strlen(parts[i]) + i;
                    if (max_part < eff) max_part = eff;
                }
                int W = lenA;
                if (W < lenBop) W = lenBop;
                if (W < lenR) W = lenR;
                if (W < max_part) W = max_part;
                int dash1 = (lenBop > (int)strlen(parts[0]) ? lenBop : (int)strlen(parts[0]));
                int dash2 = (lenR > max_part ? lenR : max_part);
                print_repeat(' ', W - lenA); fputs(A, stdout); putchar('\n');
                print_repeat(' ', W - lenBop); putchar(op); fputs(B, stdout); putchar('\n');
                print_repeat(' ', W - dash1); print_repeat('-', dash1); putchar('\n');
                for (int i = 0; i < parts_n; ++i) {
                    int lead = W - ((int)strlen(parts[i]) + i);
                    print_repeat(' ', lead); fputs(parts[i], stdout); putchar('\n');
                }
                print_repeat(' ', W - dash2); print_repeat('-', dash2); putchar('\n');
                print_repeat(' ', W - lenR); fputs(R, stdout); putchar('\n'); putchar('\n');
            }
        }

        if (parts) {
            for (int i = 0; i < parts_n; ++i) free(parts[i]);
            free(parts);
        }
        free(A);
        free(B);
        free(R);
    }

    return 0;
}