#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long a, b;
    cin >> a >> b;
    
    long long min_n = max((a + 1) / 2, (b + 1) / 2);
    long long max_n = min(a, b);

    cout << min_n << " " << max_n << "\n";
    return 0;
}