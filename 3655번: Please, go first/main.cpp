#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int T;
    if (!(cin >> T)) return 0;
    while (T--) {
        int n; string s;
        cin >> n >> s;

        array<int,128> cnt{}; cnt.fill(0);
        array<char,128> used{}; used.fill(0);
        for (char ch : s) cnt[(unsigned char)ch]++;

        long long ans = 0;
        int num_used = 0;

        for (int i = n - 1; i >= 0; --i) {
            unsigned char c = (unsigned char)s[i];
            if (!used[c]) {
                int right = (n - 1) - i;
                ans += 1LL * (num_used - right) * cnt[c];
                num_used += cnt[c];
                used[c] = 1;
            }
        }

        cout << ans * 5LL << "\n";
    }

    return 0;
}