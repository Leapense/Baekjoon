#include <bits/stdc++.h>
using namespace std;

struct Log {
    int t, s, n;
    auto operator<=>(const Log&) const = default;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int X, C, K;
    cin >> X >> C >> K;

    vector<Log> logs(K);
    for (auto& [t, s, n] : logs) {
        cin >> t >> s >> n;
    }

    sort(logs.begin(), logs.end(), [](const Log& a, const Log& b) {
        return a.t < b.t;
    });

    vector<int> studentSeat(X + 1, 0);
    vector<int> seatOwner(C + 1, 0);

    for (const auto& [t, s, n] : logs) {
        (void) t;
        if (seatOwner[s] != 0) {
            continue;
        }

        int oldSeat = studentSeat[n];
        if (oldSeat != 0) {
            seatOwner[oldSeat] = 0;
        }

        studentSeat[n] = s;
        seatOwner[s] = n;
    }

    for (int i = 1; i <= X; ++i) {
        if (studentSeat[i] != 0) {
            cout << i << ' ' << studentSeat[i] << '\n';
        }
    }

    return 0;
}