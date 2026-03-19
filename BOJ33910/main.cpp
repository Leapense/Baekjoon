#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    vector<int> A(N);
    for (int i = 0; i < N; ++i) cin >> A[i];

    long long answer = 0;
    int mn = INT_MAX;

    for (int i = N - 1; i >= 0; --i) {
        mn = min(mn, A[i]);
        answer += mn;
    }

    cout << answer;
    return 0;
}