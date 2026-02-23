#include <iostream>
#include <algorithm>
#include <limits>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    if (!(cin >> N)) return 0;

    int L = 0;
    int R = numeric_limits<int>::max();

    for (int i = 0; i < N; ++i) {
        int a, b;
        cin >> a >> b;
        L = max(L, a);
        R = min(R, b);
    }

    if (L > R) {
        cout << "bad news\n";
    } else {
        cout << (R - L + 1) << ' ' << L << "\n";
    }

    return 0;
}