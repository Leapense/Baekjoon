#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, K;
    cin >> N >> K;

    int S = (K - (N - 1)) / N;
    cout << S << "\n";
    return 0;
}