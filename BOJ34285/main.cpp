#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    unordered_map<string, pair<int, int>> pos;
    pos.reserve(static_cast<size_t>(n) * 2);

    for (int i = 0; i < n; ++i) {
        string name;
        int x, y;
        cin >> name >> x >> y;
        pos[name] = {x, y};
    }

    long long answer = 0;
    string prevName, curName;
    cin >> prevName;
    auto [px, py] = pos[prevName];

    for (int i = 1; i < n; ++i) {
        cin >> curName;
        auto [cx, cy] = pos[curName];
        answer += llabs((long long)px - cx) + llabs((long long)py - cy);
        px = cx;
        py = cy;
    }

    cout << answer;
    return 0;
}