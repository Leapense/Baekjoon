#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    while (cin >> N && N >= 3) {
        vector<double> x(N), y(N);
        for (int i = 0; i < N; ++i) {
            cin >> x[i] >> y[i];
        }

        double V;
        cin >> V;

        long double area2 = 0.0L;

        for (int i = 0; i < N; ++i) {
            int j = (i + 1) % N;
            area2 += (long double)x[i] * y[j] - (long double)x[j] * y[i];
        }

        long double area = fabsl(area2) * 0.5L;
        long double length = (long double)V / area;
        cout << "BAR LENGTH: " << fixed << setprecision(2) << (long double)length << "\n";
    }

    return 0;
}