#include <bits/stdc++.h>
using namespace std;

static int mod_pos(int a, int m) {
    int r = a % m;
    return r < 0 ? r + m : r;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string s1, s2;
    cin >> s1 >> s2;

    auto parse = [](const string& s) {
        int hh = stoi(s.substr(0, 2));
        int mm = stoi(s.substr(3, 2));
        hh %= 12;
        return 60 * hh + mm;
    };

    int t1 = parse(s1);
    int t2 = parse(s2);

    int d = mod_pos(t2 - t1, 720);
    int min_minutes = min(d, 720 - d);

    cout << min_minutes * 6;

    return 0;
}