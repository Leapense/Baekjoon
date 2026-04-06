#include <bits/stdc++.h>
using namespace std;

struct Horizontal {
    int y, x1, x2;
};

struct Vertical {
    int x, y1, y2;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int s;
        cin >> s;

        vector<Horizontal> hs;
        vector<Vertical> vs;

        for (int i = 0; i < s; ++i) {
            int x1, y1, x2, y2;
            cin >> x1 >> y1 >> x2 >> y2;

            if (y1 == y2) {
                if (x1 > x2) swap(x1, x2);
                hs.push_back({y1, x1, x2});
            } else {
                if (y1 > y2) swap(y1, y2);
                vs.push_back({x1, y1, y2});
            }
        }

        long long answer = 0;

        for (int i = 0; i < (int)hs.size(); ++i) {
            for (int j = i + 1; j < (int)hs.size(); ++j) {
                int y_low = min(hs[i].y, hs[j].y);
                int y_high = max(hs[i].y, hs[j].y);

                int left = max(hs[i].x1, hs[j].x1);
                int right = min(hs[i].x2, hs[j].x2);

                if (left > right) continue;

                int cnt = 0;
                for (const auto& v : vs) {
                    if (v.x >= left && v.x <= right && v.y1 <= y_low && v.y2 >= y_high) {
                        ++cnt;
                    }
                }

                answer += 1LL * cnt * (cnt - 1) / 2;
            }
        }

        cout << answer << '\n';
    }

    return 0;
}