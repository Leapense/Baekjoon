#include <stdio.h>
#include <stdint.h>

int main(void) {
    uint32_t N;
    if (scanf("%u", &N) != 1) return 0;

    uint8_t bytes[4];
    int len = 0;

    uint32_t temp = N;
    while (temp > 0) {
        bytes[len++] = (uint8_t)(temp & 255u);
        temp >>= 8;
    }

    uint32_t M = 0;
    for (int i = 0; i < len; ++i) {
        M = (M << 8) | bytes[i];
    }

    printf("%u\n", M);
    return 0;
}