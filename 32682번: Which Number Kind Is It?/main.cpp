#include <bits/stdc++.h>
using namespace std;

static constexpr int MAXN = 1'000'000;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    vector<char> isSquare(MAXN + 1, 0);
    for (int i = 0; i * i <= MAXN; i++) {
        isSquare[i * i] = 1;
    }

    while (T--) {
        int N;
        cin >> N;

        bool odd = (N & 1);
        bool sq = isSquare[N];

        if (odd && sq) cout << "OS\n";
        else if (odd) cout << "O\n";
        else if (sq) cout << "S\n";
        else cout << "EMPTY\n";
    }
    return 0;
}