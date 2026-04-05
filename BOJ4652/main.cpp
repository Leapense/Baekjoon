#include <iostream>
#include <string>
#include <vector>
using namespace std;

int group_of(char c) {
    if ('a' <= c && c <= 'i') return 0;
    if ('j' <= c && c <= 'r') return 1;
    return 2;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int k[3];
    while (cin >> k[0] >> k[1] >> k[2]) {
        if (k[0] == 0 && k[1] == 0 && k[2] == 0) break;

        string enc;
        cin >> enc;

        int n = static_cast<int>(enc.size());
        string dec = enc;

        vector<int> pos[3];
        for (int i = 0; i < n; ++i) {
            pos[group_of(enc[i])].push_back(i);
        }
        
        for (int g = 0; g < 3; ++g) {
            int m = static_cast<int>(pos[g].size());
            if (m == 0) continue;

            int shift = k[g] % m;
            for (int i = 0; i < m; ++i) {
                int to = (i + shift) % m;
                dec[pos[g][to]] = enc[pos[g][i]];
            }
        }

        cout << dec << '\n';
    }

    return 0;
}