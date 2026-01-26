#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> a(N);
    int even_cnt = 0, odd_cnt = 0;
    for (int i = 0; i < N; i++) {
        cin >> a[i];
        if (a[i] % 2 == 0) even_cnt++;
        else odd_cnt++;
    }

    bool target_even = (even_cnt > odd_cnt);
    int m = target_even ? even_cnt : odd_cnt;

    int limit = target_even ? 2 * (m + 1) : (2 * m + 1);
    vector<char> used(limit + 1, 0);

    for (int x : a) {
        if (target_even) {
            if (x % 2 == 0 && x <= limit) used[x] = 1;
        } else {
            if (x % 2 != 0 && x <= limit) used[x] = 1;
        }
    }

    int start = target_even ? 2 : 1;
    for (int x = start; x <= limit; x += 2) {
        if (!used[x]) {
            cout << x << "\n";
            break;
        }
    }

    return 0;
}