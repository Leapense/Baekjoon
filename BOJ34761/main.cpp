#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> A(N + 1), B(2 * N + 1);
    vector<bool> exists(N + 1, false);

    for (int i = 1; i <= N; ++i) {
        cin >> A[i];
        exists[A[i]] = true;
    }

    for (int i = 1; i <= 2 * N; ++i) {
        cin >> B[i];
    }

    for (int i = 1; i <= N; ++i) {
        if (A[i] != B[i]) {
            cout << "NO";
            return 0;
        }
    }

    for (int i = N + 1; i <= 2 * N; ++i) {
        if (!exists[B[i]]) {
            cout << "NO";
            return 0;
        }
    }

    cout << "YES";
    return 0;
}