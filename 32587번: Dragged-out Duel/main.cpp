#include <bits/stdc++.h>
using namespace std;

static bool win(char a, char b) {
    return (a == 'R' && b == 'S') || (a == 'P' && b == 'R') || (a == 'S' && b == 'P');
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    string me, guile;
    cin >> me >> guile;

    int myWins = 0, guileWins = 0;
    for (int i = 0; i < n; ++i) {
        char a = me[i], b = guile[i];
        if (a == b) continue;
        if (win(a, b)) ++myWins;
        else ++guileWins;
    }

    cout << (myWins > guileWins ? "victory" : "defeat") << '\n';
    return 0;
}