#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N;
    long long K;

    if (!(cin >> N >> K)) return 0;
    vector<long long> A(N);

    long long maxA = LLONG_MIN, minA = LLONG_MAX;
    int idxMin = -1;

    for (int i = 0; i < N; ++i) {
        cin >> A[i];
        if (A[i] > maxA) maxA = A[i];
        if (A[i] < minA) { minA = A[i]; idxMin = i; }
    }
    if (maxA > K) {
        cout << 0 << '\n';
        return 0;
    }
    if (minA <= 0) {
        cout << -1 << '\n';
        return 0;
    }

    long long m = minA;
    long long safe = 0;
    for (int i = 0; i < N; ++i) {
        if (i == idxMin) continue;
        safe += (K - A[i]) / m;
    }

    cout << (safe + 1) << '\n';
    return 0;
}