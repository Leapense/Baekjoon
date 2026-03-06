#include <array>
#include <algorithm>
#include <cctype>
#include <iostream>
#include <optional>
#include <queue>
#include <string>
#include <vector>

using namespace std;

static constexpr int TOTAL = 24 * 60;

static int encode_time(int h, int m) {
    return h * 60 + m;
}

static pair<int, int> decode_time(int id) {
    return {id / 60, id % 60};
}

static bool valid_time(int h, int m) {
    return 0 <= h && h <= 23 && 0 <= m && m <= 59;
}

static int build_neighbor(int id, int pos, int delta) {
    auto [h, m] = decode_time(id);

    array<int, 4> d{
        h / 10,
        h % 10,
        m / 10,
        m % 10
    };

    d[pos] = (d[pos] + delta + 10) % 10;

    int nh = d[0] * 10 + d[1];
    int nm = d[2] * 10 + d[3];


    if (!valid_time(nh, nm)) {
        return -1;
    }

    return encode_time(nh, nm);
}

static optional<int> parse_time(const string& s) {
    if (s.size() != 5 || s[2] != ':') {
        return nullopt;
    }

    if (!isdigit(static_cast<unsigned char>(s[0])) ||
        !isdigit(static_cast<unsigned char>(s[1])) ||
        !isdigit(static_cast<unsigned char>(s[3])) ||
        !isdigit(static_cast<unsigned char>(s[4]))) {
            return nullopt;
    }

    int h = (s[0] - '0') * 10 + (s[1] - '0');
    int m = (s[3] - '0') * 10 + (s[4] - '0');

    if (!valid_time(h, m)) {
        return nullopt;
    }

    return encode_time(h, m);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string s1, s2;
    if (!(cin >> s1 >> s2)) {
        cerr << "Invalid input.\n";
        return 1;
    }

    auto start_opt = parse_time(s1);
    auto target_opt = parse_time(s2);

    if (!start_opt || !target_opt) {
        cerr << "Time must be in valid HH:MM format.\n";
        return 1;
    }

    int start = *start_opt;
    int target = *target_opt;

    array<bool, TOTAL> visited{};
    array<int, TOTAL> prev;
    prev.fill(-1);

    queue<int> q;
    visited[start] = true;
    q.push(start);

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        if (cur == target) {
            break;
        }

        for (int pos = 0; pos < 4; ++pos) {
            for (int delta : {-1, +1}) {
                int nxt = build_neighbor(cur, pos, delta);
                if (nxt == -1 || visited[nxt]) {
                    continue;
                }

                visited[nxt] = true;
                prev[nxt] = cur;
                q.push(nxt);
            }
        }
    }

    if (!visited[target]) {
        cerr << "No path found.\n";
        return 1;
    }

    vector<int> path;
    for (int cur = target; cur != -1; cur = prev[cur]) {
        if (path.size() >= TOTAL) {
            cerr << "Internal path error.\n";
            return 1;
        }

        path.push_back(cur);
    }

    reverse(path.begin(), path.end());

    cout << path.size() << '\n';
    for (int id : path) {
        auto [h, m] = decode_time(id);
        cout << (h / 10) << (h % 10) << ':' << (m / 10) << (m % 10) << '\n';
    }

    return 0;
}