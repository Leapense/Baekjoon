#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<int> A(N + 1);
    for (int i = 1; i <= N; i++) {
        cin >> A[i];
    }

    for (int j = 0; j < M; j++) {
        int l, h;
        cin >> l >> h;

        int mx = A[1];
        for (int i = 2; i <= N; i++) mx = max(mx, A[i]);

        if (A[h] == mx) { }
        else {
            A[l]--;
        }
    }

    for (int i = 1; i <= N; i++) {
        if (i > 1) cout << ' ';
        cout << A[i];
    }

    cout << '\n';

    return 0;
}