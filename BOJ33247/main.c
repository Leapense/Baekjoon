#define _GNU_SOURCE
#include <stdio.h>
#include <stdbool.h>

#if defined(_WIN32) || defined(_WIN64)
#define GETC getchar
#else
#define GETC getchar_unlocked
#endif

static inline int read_int(void) {
    int c = GETC();
    while (c == ' ' || c == '\n' || c == '\r' || c == '\t') c = GETC();

    int sign = 1;
    if (c == '-') { sign = -1; c = GETC(); }

    int x = 0;
    while (c >= '0' && c <= '9') {
        x = x * 10 + (c - '0');
        c = GETC();
    }

    return x * sign;
}

int main(void) {
    int N = read_int();

    long long balance = 0;
    int prev_id = 0;
    bool invalid = false;
    bool no_money = false;

    for (int k = 1; k <= N; k++) {
        int id = read_int();
        int parent = read_int();
        int amount = read_int();

        if (k == 1) {
            if (parent != 0) invalid = true;
        } else {
            if (parent != prev_id) invalid = true;
        }

        balance += (long long)amount;
        if (balance < 0) no_money = true;

        prev_id = id;
    }

    if (no_money) {
        puts("NO_MONEY");
    } else if (invalid) {
        puts("INVALID");
    } else {
        printf("%lld\n", balance);
    }

    return 0;
}