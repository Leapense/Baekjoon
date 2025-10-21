#include <bits/stdc++.h>
using namespace std;

static inline bool is_leap(long long y) {
    return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
}

static long long days_from_civil(long long y, unsigned m, unsigned d) {
    y -= m <= 2;
    const long long era = (y >= 0 ? y : y - 399) / 400;
    const unsigned yoe = static_cast<unsigned>(y - era * 400);
    const unsigned doy = (153 * (m + (m > 2 ? -3 : 9)) + 2) / 5 + d - 1;
    const unsigned doe = yoe * 365 + yoe / 4 - yoe / 100 + yoe / 400 + doy;
    return era * 146097 + (long long)doe;
}

static bool valid_date(long long y, int m, int d) {
    if (m < 1 || m > 12 || d < 1) return false;
    static const int mdays_norm[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int md = mdays_norm[m - 1];
    if (m == 2 && is_leap(y)) md = 29;
    return d <= md;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T;
    if (!(cin >> T)) return 0;
    for (int tc = 0; tc < T; ++tc) {
        string due, rep;
        cin >> due >> rep;

        int dm, dd, dy;
        {
            char c1, c2;
            stringstream ss(due);
            ss >> dm >> c1 >> dd >> c2 >> dy;
        }
        int sm, sd;
        {
            char c1;
            stringstream ss(rep);
            ss >> sm >> c1 >> sd;
        }

        long long D = days_from_civil(dy, dm, dd);
        struct Cand { long long y; long long diff; bool ok; };
        vector<Cand> cs;
        for (long long cy : { (long long)dy - 1, (long long)dy, (long long)dy + 1 }) {
            if (valid_date(cy, sm, sd)) {
                long long S = days_from_civil(cy, sm, sd);
                cs.push_back({cy, S - D, true});
            }
        }

        bool found = false;
        long long best_abs = (1LL << 62), best_diff = 0, best_year = 0;
        for (const auto& c : cs) {
            long long ad = llabs(c.diff);
            if (ad < best_abs || (ad == best_abs && c.y == dy)) {
                best_abs = ad;
                best_diff = c.diff;
                best_year = c.y;
                found = true;
            }
        }

        if (!found) {
            cout << "OUT OF RANGE\n";
            continue;
        }

        if (best_abs == 0) {
            cout << "SAME DAY\n";
        } else if (best_abs <= 7) {
            cout << sm << '/' << sd << '/' << best_year << " IS " << best_abs << " " << (best_abs == 1 ? "DAY" : "DAYS") << " " << (best_diff > 0 ? "AFTER" : "PRIOR") << "\n";
        } else {
            cout << "OUT OF RANGE\n";
        }
    }

    return 0;
}