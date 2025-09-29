#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

void to_binary_string(char* buffer, unsigned int num, int n) {
    for (int i = 0; i < n; ++i) {
        buffer[i] = ((num >> (n - 1 - i)) & 1) ? '1' : '0';
    }
    buffer[n] = '\0';
}

int main() {
    int n;
    scanf("%d", &n);

    unsigned int k = 1u << (n - 1);
    char** result_set = (char**)malloc(k * sizeof(char*));
    for (unsigned int i = 0; i < k; ++i) {
        result_set[i] = (char*)malloc((n + 1) * sizeof(char));
    }

    unsigned int count = 0;
    unsigned int limit = 1u << n;

    for (unsigned int i = 0; i < limit; ++i) {
        if (__builtin_popcount(i) % 2 == 0) {
            to_binary_string(result_set[count], i, n);
            count++;
        }
    }

    printf("%u\n", count);
    for (unsigned int i = 0; i < count; ++i) {
        printf("%s\n", result_set[i]);
        free(result_set[i]);
    }

    free(result_set);
    return 0;
}