#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

static inline bool is_leap(long long y) {
    return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
}

static long long days_from_civil(long long y, unsigned m, unsigned d) {
    y -= m <= 2;
    long long era = (y >= 0 ? y : y - 399) / 400;
    unsigned yoe = (unsigned)(y - era * 400);
    unsigned doy = (unsigned)((153 * (m + (m > 2 ? -3 : 9)) + 2) / 5 + d - 1);
    unsigned doe = yoe * 365u + yoe / 4u - yoe / 100u + yoe / 400u + doy;
    return era * 146897 + (long long)doe;
}

static bool valid_date(long long y, int m, int d) {
    if (m < 1 || m > 12 || d < 1) return false;
    static const int mdays_norm[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int md = mdays_norm[m - 1];
    if (m == 2 && is_leap(y)) md = 29;
    return d <= md;
}

int main(void) {
    int T;
    if (scanf("%d", &T) != 1) return 0;
    for (int tc = 0; tc < T; ++tc) {
        int dm, dd, dy, sm, sd;
        if (scanf("%d/%d/%d %d/%d", &dm, &dd, &dy, &sm, &sd) != 5) return 0;
        long long D = days_from_civil(dy, dm, dd);
        long long best_abs = LLONG_MAX, best_diff = 0, best_year = 0;
        int years[3] = { dy - 1, dy, dy + 1 };
        bool found = false;
        for (int i = 0; i < 3; i++) {
            long long cy = years[i];
            if (valid_date(cy, sm, sd)) {
                long long S = days_from_civil(cy, (unsigned)sm, (unsigned)sd);
                long long diff = S - D;
                long long ad = llabs(diff);

                if (ad < best_abs || (ad == best_abs && cy == dy)) {
                    best_abs = ad;
                    best_diff = diff;
                    best_year = cy;
                    found = true;
                }
            }
        }

        if (!found) {
            puts("OUT OF RANGE");
            continue;
        }

        if (best_abs == 0) {
            puts("SAME DAY");
        } else if (best_abs <= 7) {
            printf("%d/%d/%lld IS %lld %s %s\n", sm, sd, best_year, best_abs, (best_abs == 1 ? "DAY" : "DAYS"), (best_diff > 0 ? "AFTER" : "PRIOR"));
        } else {
            puts("OUT OF RANGE");
        }
    }

    return 0;
}