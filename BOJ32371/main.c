#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define ROWS 4
#define COLS 10
#define KERNEL_SIZE 9

static int cmp_char(const void *a, const void *b) {
    const unsigned char x = *(const unsigned char *)a;
    const unsigned char y = *(const unsigned char *)b;
    return (x > y) - (x < y);
}

int main(void) {
    char kb[ROWS][COLS + 1];
    char s[16];

    for (int i = 0; i < ROWS; i++) {
        if (scanf("%10s", kb[i]) != 1) return 0;
    }

    if (scanf("%15s", s) != 1) return 0;

    if (strlen(s) != KERNEL_SIZE) {
        return 0;
    }

    char target[KERNEL_SIZE + 1];
    strcpy(target, s);
    qsort(target, KERNEL_SIZE, sizeof(char), cmp_char);

    for (int r = 1; r < ROWS - 1; r++) {
        for (int c = 1; c < COLS - 1; c++) {
            char a[KERNEL_SIZE];
            int idx = 0;

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    a[idx++] = kb[r + dr][c + dc];
                }
            }

            qsort(a, KERNEL_SIZE, sizeof(char), cmp_char);
            if (memcmp(a, target, KERNEL_SIZE) == 0) {
                putchar(kb[r][c]);
                putchar('\n');
                return 0;
            }
        }
    }

    return 0;
}