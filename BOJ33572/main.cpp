#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    long long M;
    cin >> N >> M;

    __int128 cap = 0;
    for (int i = 0; i < N; i++) {
        long long A;
        cin >> A;
        cap += (__int128)(A - 1);
        if (cap >= (__int128)M) {
            cout << "DIMI\n";
            return 0;
        }
    }

    cout << (cap >= (__int128)M ? "DIMI\n" : "OUT\n");

    return 0;
}