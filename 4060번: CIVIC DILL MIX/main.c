#include <stdio.h>
#include <string.h>

static inline int rvalue(char c) {
    switch (c) {
        case 'I': return 1;
        case 'V': return 5;
        case 'X': return 10;
        case 'L': return 50;
        case 'C': return 100;
        case 'D': return 500;
        case 'M': return 1000;
        default: return 0;
    }
}

int roman_to_int(const char *s) {
    int sum = 0;
    for (int i = 0; s[i]; ++i) {
        int v = rvalue(s[i]);
        int next = s[i + 1] ? rvalue(s[i + 1]) : 0;
        if (v < next) sum -= v;
        else sum += v;
    }

    return sum;
}

void int_to_roman(int x, char out[], size_t outsz) {
    struct Item { int val; const char *sym; };
    static const struct Item table[] = {
        {1000, "M"},
        {900, "CM"},
        {500, "D"},
        {400, "CD"},
        {100, "C"},
        {90, "XC"},
        {50, "L"},
        {40, "XL"},
        {10, "X"},
        {9, "IX"},
        {5, "V"},
        {4, "IV"},
        {1, "I"}
    };

    size_t pos = 0;
    for (size_t i = 0; i < sizeof(table)/sizeof(table[0]); ++i) {
        while (x >= table[i].val) {
            const char *s = table[i].sym;
            for (size_t j = 0; s[j]; ++j) {
                if (pos + 1 < outsz) out[pos++] = s[j];
            }
            x -= table[i].val;
        }
    }
    if (pos < outsz) out[pos] = '\0';
    else out[outsz - 1] = '\0';
}

int main(void) {
    int n, caseNo = 1;
    while (scanf("%d", &n) == 1) {
        if (n == 0) break;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            char tok[64];
            scanf("%63s", tok);
            sum += roman_to_int(tok);
        }
        char caseRoman[64], sumRoman[64];
        int_to_roman(caseNo++, caseRoman, sizeof(caseRoman));
        int_to_roman(sum, sumRoman, sizeof(sumRoman));
        printf("Case %s: %s\n", caseRoman, sumRoman);
    }

    return 0;
}