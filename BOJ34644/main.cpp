#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    array<int, 10> maxPos{};
    maxPos.fill(0);

    for (int pos = 1; pos <= 100; pos++) {
        int x;
        cin >> x;
        int d = x % 10;
        maxPos[d] = max(maxPos[d], pos);
    }

    int bestDigit = 0;
    for (int d = 1; d <= 9; d++) {
        if (maxPos[d] > maxPos[bestDigit]) bestDigit = d;
    }

    int answerPlayer = (bestDigit == 0) ? 10 : bestDigit;
    cout << answerPlayer;
    return 0;
}