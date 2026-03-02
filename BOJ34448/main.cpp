#include <iostream>
#include <string>
#include <vector>

using namespace std;

static bool is_base_row(const string& s, int& first, int& last) {
    first = -1;
    last = -1;

    for (int i = 0; i < (int)s.size(); i++) {
        if (s[i] == '#') { first = i; break; }
    }
    if (first == -1) return false;
    for (int i = (int)s.size() - 1; i >= 0; i--) {
        if (s[i] == '#') { last = i; break; }
    }

    int w = last - first + 1;
    if (w < 3) return false;

    for (int i = first; i <= last; i++) {
        if (s[i] != '#') return false;
    }

    return true;
}

static char at_or_space(const string& s, int idx) {
    if (idx < 0) return ' ';
    if (idx >= (int)s.size()) return ' ';
    return s[idx];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    vector<string> rows;
    string line;
    while (getline(cin, line)) {
        rows.push_back(line);
    }

    if (rows.empty()) {
        return 0;
    }

    int base_idx = -1, base_l = -1, base_r = -1;
    for (int i = 0; i < (int)rows.size(); i++) {
        int l, r;
        if (is_base_row(rows[i], l, r)) {
            base_idx = i;
            base_l = l;
            base_r = r;
            break;
        }
    }

    if (base_idx == -1) {
        return 0;
    }

    int p = base_idx;
    cout << p << "\n";

    int first_tine_row = base_idx + 1;

    vector<int> tine_cols;

    if (first_tine_row < (int)rows.size()) {
        for (int c = base_l; c <= base_r; c++) {
            if (at_or_space(rows[first_tine_row], c) == '#') {
                tine_cols.push_back(c);
            }
        }
    }

    vector<int> lens(tine_cols.size(), 0);
    for (int i = base_idx + 1; i < (int)rows.size(); i++) {
        for (int j = 0; j < (int)tine_cols.size(); j++) {
            if (at_or_space(rows[i], tine_cols[j]) == '#') lens[j]++;
        }
    }

    for (int i = 0; i < (int)lens.size(); i++) {
        if (i) cout << ' ';
        cout << lens[i];
    }
    cout << "\n";
    return 0;
}