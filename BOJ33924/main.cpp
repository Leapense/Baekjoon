#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M, K;
    cin >> N >> M;
    cin >> K;
    string S;
    cin >> S;

    int id = (N - 1) * 2 + (M - 1);

    constexpr array<int, 8> permA{4, 5, 6, 7, 0, 1, 2, 3};
    constexpr array<int, 8> permB{3, 2, 1, 0, 7, 6, 5, 4};
    constexpr array<int, 8> permC{7, 6, 5, 4, 3, 2, 1, 0};
    constexpr array<int, 8> permD{1, 3, 0, 5, 2, 7, 4, 6};

    for (char ch : S) {
        if (ch == 'A') id = permA[id];
        else if (ch == 'B') id = permB[id];
        else if (ch == 'C') id = permC[id];
        else if (ch == 'D') id = permD[id];
    }

    cout << (id / 2 + 1) << ' ' << (id % 2 + 1);

    return 0;
}