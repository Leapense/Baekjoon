#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

int main(void) {
    char line[256];
    const double PI = acos(-1.0);
    while (fgets(line, sizeof(line), stdin)) {
        size_t len = strlen(line);
        while (len > 0 && (line[len - 1] == '\n' || line[len - 1] == '\r')) {
            line[--len] = '\0';
        }

        if (len == 0) continue;

        char original[256];
        strcpy(original, line);

        char *tokens[16];
        int tcnt = 0;

        char *p = strtok(line, " ");
        while (p && tcnt < 16) {
            tokens[tcnt++] = p;
            p = strtok(NULL, " ");
        }

        int width_mm = atoi(tokens[1]);
        int ratio_pct = atoi(tokens[3]);
        int rim_in = atoi(tokens[tcnt - 1]);

        double section_height_mm = (double)width_mm * (double)ratio_pct / 100.0;
        double rim_mm = (double)rim_in * 25.4;
        double overall_diameter_mm = rim_mm + 2.0 * section_height_mm;
        double circumference_cm = PI * (overall_diameter_mm / 10.0);
        long long ans = llround(circumference_cm);

        printf("%s: %lld\n", original, ans);
    }

    return 0;
}