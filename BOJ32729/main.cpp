#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);


    string letters;
    cin >> letters;

    array<int, 26> avail{};
    for (char c : letters) {
        avail[c - 'a']++;
    }

    int N;
    cin >> N;

    for (int i = 0; i < N; ++i) {
        string w;
        cin >> w;

        array<int, 26> need{};
        for (char c : w) need[c - 'a']++;

        bool ok = true;
        for (int k = 0; k < 26; ++k) {
            if (need[k] > avail[k]) {
                ok = false;
                break;
            }
        }

        if (ok) cout << w << '\n';
    }

    return 0;
}