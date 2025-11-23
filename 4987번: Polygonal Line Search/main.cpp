#include <bits/stdc++.h>
using namespace std;

struct Vec {
    int dx;
    int dy;
};

static void rotate90(vector<Vec>& v) {
    for (auto& e : v) {
        int dx = e.dx, dy = e.dy;
        e.dx = -dy;
        e.dy = dx;
    }
}

static vector<Vec> reverse_vec(const vector<Vec>& src) {
    int len = (int)src.size();
    vector<Vec> dst(len);
    for (int i = 0; i < len; ++i) {
        dst[i].dx = -src[len - 1 - i].dx;
        dst[i].dy = -src[len - 1 - i].dy;
    }
    return dst;
}

static bool less_seq(const vector<Vec>& a, const vector<Vec>& b) {
    int len = (int)a.size();
    for (int i = 0; i < len; ++i) {
        if (a[i].dx != b[i].dx) return a[i].dx < b[i].dx;
        if (a[i].dy != b[i].dy) return a[i].dy < b[i].dy;
    }
    return false;
}

static bool equal_seq(const vector<Vec>& a, const vector<Vec>& b) {
    int len = (int)a.size();
    if ((int)b.size() != len) return false;
    for (int i = 0; i < len; ++i) {
        if (a[i].dx != b[i].dx || a[i].dy != b[i].dy) return false;
    }
    return true;
}

static vector<Vec> canonical(const vector<Vec>& edge) {
    int len = (int)edge.size();
    vector<Vec> rot = edge;
    vector<Vec> best;
    bool has_best = false;

    for (int k = 0; k < 4; ++k) {
        if (!has_best || less_seq(rot, best)) {
            best = rot;
            has_best = true;
        }
        auto rev = reverse_vec(rot);
        if (less_seq(rev, best)) {
            best = rev;
        }
        rotate90(rot);
    }
    return best;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    while (true) {
        int n;
        if (!(cin >> n)) return 0;
        if (n == 0) break;

        int m0;
        cin >> m0;
        vector<pair<int, int>> p0(m0);
        for (int i = 0; i < m0; ++i) {
            cin >> p0[i].first >> p0[i].second;
        }

        vector<Vec> edges0;
        edges0.reserve(m0 - 1);
        for (int i = 0; i + 1 < m0; ++i) {
            int dx = p0[i + 1].first - p0[i].first;
            int dy = p0[i + 1].second - p0[i].second;
            edges0.push_back({dx, dy});
        }
        auto canon0 = canonical(edges0);
        int L0 = (int)edges0.size();

        for (int idx = 1; idx <= n; ++idx) {
            int mi;
            cin >> mi;
            vector<pair<int, int>> pi(mi);
            for (int i = 0; i < mi; ++i) {
                cin >> pi[i].first >> pi[i].second;
            }
            if (mi != m0) {
                continue;
            }

            vector<Vec> edges;
            edges.reserve(mi - 1);
            for (int i = 0; i + 1 < mi; ++i) {
                int dx = pi[i + 1].first - pi[i].first;
                int dy = pi[i + 1].second - pi[i].second;
                edges.push_back({dx, dy});
            }

            auto canoni = canonical(edges);
            if (equal_seq(canon0, canoni)) {
                cout << idx << '\n';
            }
        }

        cout << "+++++\n";
    }

    return 0;
}