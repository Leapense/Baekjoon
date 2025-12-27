#include <bits/stdc++.h>
using namespace std;

static inline int mx(int a, int b) { return a > b ? a : b; }
static inline int mn(int a, int b) { return a < b ? a : b; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    string A, B;
    cin >> A >> B;
    
    int i0 = 1;
    while (i0 <= N && A[i0 - 1] == B[i0 - 1]) i0++;

    int j0 = N;
    while (j0 >= 1 && A[j0 - 1] == B[j0 - 1]) j0--;

    int K = i0 + j0;

    vector<int> bad(N + 1, 0);
    for (int i = 1; i <= N; i++) {
        int j = K - i;
        bool ok = (1 <= j && j <= N && B[i - 1] == A[j - 1]);
        bad[i] = bad[i - 1] + (ok ? 0 : 1);
    }

    int Lmin = mx(1, K - N);
    int Lmax = mn(i0, (K - 1) / 2);

    for (int l = Lmin; l <= Lmax; l++) {
        int r = K - l;
        if (r > N || r <= l) continue;
        if (bad[r] - bad[l - 1] == 0) {
            cout << l << " " << r << "\n";
            return 0;
        }
    }

    return 0;
}