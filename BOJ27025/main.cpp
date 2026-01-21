#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    int cur = 0, best = 0;
    for (int i = 0; i < N; i++) {
        int a, b;
        cin >> a >> b;
        if (a == 0 && b == 0) {
            cur++;
            best = max(best, cur);
        } else {
            cur = 0;
        }
    }

    cout << 2 * best << '\n';
    return 0;
}