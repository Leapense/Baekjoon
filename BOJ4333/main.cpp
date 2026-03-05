#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    const array<string, 12> names = {
        "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"
    };

    unordered_map<string, int> idx;
    idx.reserve(16);
    for (int i = 0; i < 12; i++) idx[names[i]] = i;

    array<uint16_t, 12> scaleMask{};
    const array<int, 7> steps = {0, 2, 4, 5, 7, 9, 11};

    for (int r = 0; r < 12; r++) {
        uint16_t m = 0;
        for (int s : steps) {
            int p = (r + s) % 12;
            m |= uint16_t(1u << p);
        }
        scaleMask[r] = m;
    }

    string line;
    while (getline(cin, line)) {
        if (line == "END") break;

        istringstream iss(line);
        string tok;
        uint16_t used = 0;
        while (iss >> tok) {
            auto it = idx.find(tok);
            if (it != idx.end()) used |= uint16_t(1u << it->second);
        }

        bool first = true;
        for (int r = 0; r < 12; r++) {
            if ((used & uint16_t(~scaleMask[r])) == 0) {
                if (!first) cout << ' ';
                cout << names[r];
                first = false;
            }
        }

        cout << '\n';
    }

    return 0;
}