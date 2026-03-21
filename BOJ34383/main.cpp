#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    long long sum = 0;
    long long minPrefix = 0;

    for (int i = 0; i < N; ++i) {
        char type;
        long long A;
        cin >> type >> A;

        if (type == 'T') sum -= A;
        else sum += A;

        minPrefix = min(minPrefix, sum);
    }

    cout << -minPrefix << '\n';
    return 0;
}
