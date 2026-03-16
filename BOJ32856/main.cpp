#include <algorithm>
#include <iostream>
#include <limits>
#include <string>
#include <utility>
#include <vector>

using namespace std;

struct Orientation {
    int h, w;
    vector<pair<int, int>> cells;

    bool operator==(const Orientation& other) const {
        return h == other.h && w == other.w && cells == other.cells;
    }
};

static void normalize(Orientation& o) {
    int min_r = numeric_limits<int>::max();
    int min_c = numeric_limits<int>::max();

    for (auto [r, c] : o.cells) {
        min_r = min(min_r, r);
        min_c = min(min_c, c);
    }

    for (auto& [r, c] : o.cells) {
        r -= min_r;
        c -= min_c;
    }

    sort(o.cells.begin(), o.cells.end());
}

static Orientation rotate90(const Orientation& cur) {
    Orientation nxt;
    nxt.h = cur.w;
    nxt.w = cur.h;
    nxt.cells.reserve(cur.cells.size());
    for (auto [r, c] : cur.cells) {
        nxt.cells.push_back({c, cur.h - 1 - r});
    }

    normalize(nxt);
    return nxt;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int r, c;
    cin >> r >> c;

    vector<vector<int>> land(r, vector<int>(c));
    int total = 0;
    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            cin >> land[i][j];
            total += land[i][j];
        }
    }

    int s, t;
    cin >> s >> t;

    Orientation start;
    start.h = s;
    start.w = t;

    for (int i = 0; i < s; ++i) {
        string row;
        cin >> row;
        for (int j = 0; j < t; ++j) {
            if (row[j] == '#') {
                start.cells.push_back({i, j});
            }
        }
    }
    normalize(start);

    vector<Orientation> rots;
    Orientation cur = start;

    for (int rep = 0; rep < 4; ++rep) {
        bool exists = false;
        for (const auto& x : rots) {
            if (x == cur) {
                exists = true;
                break;
            }
        }

        if (!exists) rots.push_back(cur);
        cur = rotate90(cur);
    }

    int min_cut = numeric_limits<int>::max();

    for (const auto& o : rots) {
        for (int top = 0; top + o.h <= r; ++top) {
            for (int left = 0; left + o.w <= c; ++left) {
                int cut = 0;
                for (auto [dr, dc] : o.cells) {
                    cut += land[top + dr][left + dc];
                }

                min_cut = min(min_cut, cut);
            }
        }
    }

    cout << total - min_cut;
    return 0;
}