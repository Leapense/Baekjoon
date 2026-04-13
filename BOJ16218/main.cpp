#include <bits/stdc++.h>
using namespace std;

static constexpr int NONE = -2;

inline int eval(int sa, int sb, bool used, int K) {
    if (sa >= K && sb >= K) return used ? -1 : 1;
    if (sa - sb >= 50) return 1;
    if (sa >= K) return 1;
    if (sb >= K) return -1;
    return NONE;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    if (!(cin >> N >> K)) return 0;

    vector<int> A(N + 1), B(N + 1);

    int baseEnd = N + 1;
    int baseSA = 0, baseSB = 0;
    int sa = 0, sb = 0;

    for (int i = 1; i <= N; ++i) {
        cin >> A[i] >> B[i];

        if (baseEnd == N + 1) {
            sa += A[i];
            sb += B[i];

            int r = eval(sa, sb, false, K);
            if (r != NONE) {
                baseEnd = i;
                baseSA = sa;
                baseSB = sb;
            }
        }
    }

    int answer = 0;
    int futureUsedResult = 0;

    if (baseEnd != N + 1) {
        answer = eval(baseSA, baseSB, false, K);
        futureUsedResult = eval(baseSA, baseSB, true, K);
    }

    if (answer == 1) {
        cout << 1 << "\n";
        return 0;
    }

    sa = 0;
    sb = 0;
    int limit = (baseEnd == N + 1) ? N : baseEnd;

    for (int i = 1; i <= limit; ++i) {
        sa += A[i];
        sb += B[i];

        int modSA = sa + (A[i] / 2);
        int modSB = sb;

        int r = eval(modSA, modSB, true, K);
        if (r == NONE) {
            r = futureUsedResult;
        }

        if (r > answer) {
            answer = r;
            if (answer == 1) break;
        }
    }

    cout << answer;
    return 0;
}