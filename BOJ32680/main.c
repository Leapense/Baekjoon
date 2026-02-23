#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#define BUFFER_SIZE 65536

char buffer[BUFFER_SIZE];
size_t buffer_pos = 0;
size_t buffer_len = 0;

static inline int read_char(void) {
    if (buffer_pos >= buffer_len) {
        buffer_pos = 0;
        buffer_len = fread(buffer, 1, BUFFER_SIZE, stdin);
        if (buffer_len == 0) return EOF;
    }

    return buffer[buffer_pos++];
}

static inline int read_int(void) {
    int x = 0;
    int c = read_char();

    while (c <= ' ') {
        if (c == EOF) return 0;
        c = read_char();
    }

    while (c >= '0' && c <= '9') {
        x = x * 10 + (c - '0');
        c = read_char();
    }

    return x;
}

int main(void) {
    int N = read_int();

    int L = INT_MIN;
    int R = INT_MAX;

    for (int i = 0; i < N; ++i) {
        int a = read_int();
        int b = read_int();

        if (a > L) L = a;
        if (b < R) R = b;
    }

    if (L > R) {
        puts("bad news");
    } else {
        int count = R - L + 1;
        printf("%d %d\n", count, L);
    }

    return 0;
}