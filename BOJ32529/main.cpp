#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    long long M;
    cin >> N >> M;

    vector<int> A(N);
    for (int i = 0; i < N; i++) cin >> A[i];

    long long sum = 0;
    int ans = -1;

    for (int i = N - 1; i >= 0; i--) {
        sum += (long long)A[i];
        if (sum >= M) {
            ans = i + 1;
            break;
        }
    }

    cout << ans << "\n";
    return 0;
}