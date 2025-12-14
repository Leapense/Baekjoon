#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    vector<int> A(M + 1);
    for (int i = 1; i <= M; i++) cin >> A[i];

    vector<vector<long long>> dist(N + 1, vector<long long>(N + 1));
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> dist[i][j];
        }
    }

    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                long long via = dist[i][k] + dist[k][j];
                if (via < dist[i][j]) dist[i][j] = via;
            }
        }
    }

    long long ans = 0;
    for (int i = 1; i < M; i++) ans += dist[A[i]][A[i + 1]];

    cout << ans << "\n";
    return 0;
}