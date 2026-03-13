#include <bits/stdc++.h>
using namespace std;

struct W { long long p, l, a; };

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    long long w;
    cin >> n >> w;
    
    vector<W> v;
    while (n--) {
        char t;
        cin >> t;
        if (t == '!') {
            long long p, l, a;
            cin >> p >> l >> a;
            v.push_back({p, l, a});
        } else {
            long long x, ans = 0;
            cin >> x;
            for (auto &e : v) {
                if (x < e.p || x >= e.p + e.l) continue;
                long long d = x - e.p;
                if (d & 1LL) continue;
                ans += (d % 4 == 0 ? e.a : -e.a);
            }
            cout << ans << '\n';
        }
    }

    return 0;
}