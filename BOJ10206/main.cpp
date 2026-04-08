#include <bits/stdc++.h>
using namespace std;

static int note_index(char c) {
    switch (c) {
        case 'C': return 0;
        case 'D': return 2;
        case 'E': return 4;
        case 'F': return 5;
        case 'G': return 7;
        case 'A': return 9;
        case 'B': return 11;
        default: return -1;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int K;
    cin >> K;

    for (int tc = 1; tc <= K; ++tc) {
        int n, m;
        cin >> n >> m;

        vector<array<double, 12>> penalty(n);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 12; ++j) {
                cin >> penalty[i][j];
            }
        }

        cout << "Data Set " << tc << ":\n";

        for (int melody_idx = 0; melody_idx < m; ++melody_idx) {
            string s;
            cin >> s;

            int best_key = -1;
            double best_sum = numeric_limits<double>::infinity();

            for (int key = 0; key < n; ++key) {
                double sum = 0.0;
                for (int i = 0; i < (int)s.size(); ) {
                    int idx = note_index(s[i]);
                    if (i + 1 < (int)s.size() && s[i + 1] == '#') {
                        ++idx;
                        i += 2;
                    } else {
                        i += 1;
                    }
                    sum += penalty[key][idx];
                }

                if (sum < best_sum) {
                    best_sum = sum;
                    best_key = key + 1;
                }
            }

            cout << best_key << '\n';
        }

        cout << '\n';
    }

    return 0;
}