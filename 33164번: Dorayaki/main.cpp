#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<int> A(N), B(M);
    for (int i = 0; i < N; i++) cin >> A[i];
    for (int j = 0; j < M; j++) cin >> B[j];

    long long ans = 0;
    for (int a : A) {
        for (int b : B) {
            int mx = max(a, b);
            ans += 1LL * (a + b) * mx;
        }
    }

    cout << ans << "\n";
    return 0;
}
