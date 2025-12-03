#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    if (!(cin >> T)) return 0;

    while (T--) {
        long long N;
        cin >> N;

        bool found = false;
        long long bestA = 0, bestR = 0;

        for (long long i = 1; i * i <= N; ++i) {
            if (N % i != 0) continue;

            long long d1 = i;
            long long d2 = N / i;

            // d1, d2의 짝·홀이 같아야 r, a가 정수
            if ((d1 + d2) % 2 != 0) continue;

            long long a = (d2 - d1) / 2;
            long long r = (d1 + d2) / 2;

            if (!found || a < bestA || (a == bestA && r < bestR)) {
                found = true;
                bestA = a;
                bestR = r;
            }
        }

        if (!found) {
            cout << "IMPOSSIBLE\n";
        } else {
            cout << bestA << ' ' << bestR << '\n';
        }
    }

    return 0;
}