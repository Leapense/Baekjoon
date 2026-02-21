#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <array>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    constexpr int ROWS = 4;

    vector<string> kb(ROWS);
    for (int i = 0; i < ROWS; i++) {
        cin >> kb[i];
    }

    string s;
    if (!(cin >> s)) return 0;

    string target = s;
    ranges::sort(target);

    for (int r = 1; r < ROWS - 1; r++) {
        int cols = kb[r].size();
        for (int c = 1; c < cols - 1; c++) {
            string window;
            window.reserve(9);

            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (c + dc < kb[r + dr].size()) {
                        window.push_back(kb[r + dr][c + dc]);
                    }
                }
            }

            if (window.size() != 9) continue;

            ranges::sort(window);

            if (window == target) {
                cout << kb[r][c] << "\n";
                return 0;
            }
        }
    }

    return 0;
}