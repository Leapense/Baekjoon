#include <stdio.h>
#include <stdint.h>

typedef struct {
    int64_t p, l, a;
} Wave;

static int64_t contribution(Wave w, int64_t x) {
    if (x < w.p || x >= w.p + w.l) return 0;

    int64_t d = x - w.p;
    if (d & 1LL) return 0;
    return (d % 4 == 0) ? w.a : -w.a;
}

int main(void) {
    int n;
    int64_t W;
    if (scanf("%d %lld", &n, &W) != 2) return 0;

    Wave waves[4000];
    int cnt = 0;

    for (int i = 0; i < n; ++i) {
        char type;
        scanf(" %c", &type);

        if (type == '!') {
            int64_t p, l, a;
            scanf("%lld %lld %lld", &p, &l, &a);
            waves[cnt++] = (Wave){p, l, a};
        } else {
            int64_t x;
            scanf("%lld", &x);

            int64_t ans = 0;
            for (int j = 0; j < cnt; ++j) {
                ans += contribution(waves[j], x);
            }

            printf("%lld\n", ans);
        }
    }

    return 0;
}