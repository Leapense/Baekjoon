#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int C, N;
    cin >> C >> N;

    long long L = C / N;

    vector<long long> X(N);
    for (auto &x : X) cin >> x;

    vector<long long> P(2 * N), B(2 * N);
    for (int i = 0; i < 2 * N; ++i) {
        P[i] = X[i % N] + (i >= N ? C : 0);
        B[i] = P[i] - 1LL * i * L;
    }

    deque<int> mx, mn;

    for (int i = 0; i < 2 * N; ++i) {
        while (!mx.empty() && B[mx.back()] <= B[i]) mx.pop_back();
        mx.push_back(i);

        while (!mn.empty() && B[mn.back()] >= B[i]) mn.pop_back();
        mn.push_back(i);

        if (i >= N - 1) {
            int s = i - (N - 1);

            while (!mx.empty() && mx.front() < s) mx.pop_front();
            while (!mn.empty() && mn.front() < s) mn.pop_front();

            if (s < N) {
                long long diff = B[mx.front()] - B[mn.front()];
                if (diff < L) {
                    cout << 'S';
                    return 0;
                }
            }
        }
    }

    cout << 'N';
    return 0;
}