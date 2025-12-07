#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <math.h>

#define OLD_SCHEME_LIMIT 175760000LL

int new_char_map[26];

void init_map() {
    int valid_idx = 0;
    const char *invalid_chars = "ACMIP";
    for (int i = 0; i < 26; i++) {
        char c = 'A' + i;
        bool is_invalid = false;
        for (int j = 0; j < 5; j++) {
            if (c == invalid_chars[j]) {
                is_invalid = true;
                break;
            }
        }

        if (is_invalid) {
            new_char_map[i] = -1;
        } else {
            new_char_map[i] = valid_idx++;
        }
    }
}

long long get_plate_value(const char *s) {
    if (strlen(s) != 7) return -1;

    for (int i = 0; i < 3; i++) {
        if (!isupper(s[i])) return -1;
    }

    for (int i = 5; i < 7; i++) {
        if (!isdigit(s[i])) return -1;
    }

    if (isdigit(s[3])) {
        if (!isdigit(s[4])) return -1;

        long long letters = 0;
        for (int i = 0; i < 3; i++) {
            letters = letters * 26 + (s[i] - 'A');
        }

        long long digits = 0;
        for (int i = 3; i < 7; i++) {
            digits = digits * 10 + (s[i] - '0');
        }

        return letters * 10000 + digits;
    } else {
        if (!isupper(s[4])) return -1;
        long long letters = 0;
        for (int i = 0; i < 5; i++) {
            int val = new_char_map[s[i] - 'A'];
            if (val == -1) return -1;
            letters = letters * 21 + val;
        }
        long long digits = 0;
        for (int i = 5; i < 7; i++) {
            digits = digits * 10 + (s[i] - '0');
        }

        return OLD_SCHEME_LIMIT + (letters * 100 + digits);
    }
}

int main() {
    init_map();
    char sm[10], si[10];
    long long c;
    while (scanf("%s %s %lld", sm, si, &c) == 3) {
        if (strcmp(sm, "*") == 0 && strcmp(si, "*") == 0 && c == 0) break;
        long long val_m = get_plate_value(sm);
        long long val_i = get_plate_value(si);

        if (val_i != -1 && val_i > val_m && val_i <= val_m + c) {
            printf("Y\n");
        } else {
            printf("N\n");
        }
    }

    return 0;
}