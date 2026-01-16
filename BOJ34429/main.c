#include <stdio.h>
#include <string.h>

static int to_minutes_24(int h12, int m, const char *ap) {
    int h24;
    if (strcmp(ap, "AM") == 0) {
        h24 = (h12 == 12) ? 0 : h12;
    } else {
        h24 = (h12 == 12) ? 12 : (h12 + 12);
    }
    
    return h24 * 60 + m;
}

static void from_minutes_24(int minutes, int *h12, int *m, const char **ap) {
    int h24 = minutes / 60;
    *m = minutes % 60;
    
    if (h24 < 12) *ap = "AM";
    else *ap = "PM";
    
    if (h24 == 0) *h12 = 12;
    else if (h24 <= 12) *h12 = h24;
    else *h12 = h24 - 12;
}

int main(void) {
    char line[64];
    int h, m;
    char ap[3] = {0};
    int t;
    
    if (!fgets(line, sizeof(line), stdin)) return 0;
    if (sscanf(line, "%d:%d %2s", &h, &m, ap) != 3) return 0;
    if (scanf("%d", &t) != 1) return 0;
    
    int meeting = to_minutes_24(h, m, ap);
    int leave = meeting - t;
    leave = ((leave % 1440) + 1440) % 1440;
    
    int out_h, out_m;
    const char *out_ap;
    from_minutes_24(leave, &out_h, &out_m, &out_ap);
    
    printf("%d:%02d %s", out_h, out_m, out_ap);
    return 0;
}