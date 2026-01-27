#define _GNU_SOURCE
#include <stdio.h>
#include <stdint.h>

long long readLong() {
    long long x = 0;
    char c = getchar_unlocked();
    while (c < '0' || c > '9') c = getchar_unlocked();
    while (c >= '0' && c <= '9') {
        x = x * 10 + (c - '0');
        c = getchar_unlocked();
    }

    return x;
}

int main(void) {
    int N = (int)readLong();
    long long M = readLong();

    __int128 cap = 0;
    for (int i = 0; i < N; i++) {
        long long A = readLong();
        cap += (__int128)(A - 1);

        if (cap >= (__int128)M) {
            for (int j = i + 1; j < N; j++) A = readLong();
            puts("DIMI");
            return 0;
        }
    }
    puts(cap >= (__int128)M ? "DIMI" : "OUT");
    return 0;
}