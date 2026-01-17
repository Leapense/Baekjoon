#include <stdio.h>
#include <stdbool.h>

int main(void) {
    bool seen[5] = {false};
    for (int i = 0; i < 10; ++i) {
        int d;
        if (scanf("%d", &d) != 1) return 0;
        if (1 <= d && d <= 4) seen[d] = true;
    }

    int missing = 0;
    for (int lvl = 1; lvl <= 4; ++lvl) {
        if (!seen[lvl]) ++missing;
    }

    printf("%d\n", missing);
    return 0;
}
