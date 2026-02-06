#include <bits/stdc++.h>
using namespace std;

vector<int> computeZ(const string& s) {
    int n = s.size();
    vector<int> z(n, 0);
    int l = 0, r = 0;
    for (int i = 1; i < n; i++) {
        if (i < r) z[i] = min(r - i, z[i - l]);
        while (i + z[i] < n && s[z[i]] == s[i + z[i]]) z[i]++;
        if (i + z[i] > r) { l = i; r = i + z[i]; }
    }

    return z;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    for (int t = 1; t <= T; t++) {
        string s;
        cin >> s;
        int n = s.size();
        auto z = computeZ(s);

        int M = 0;
        for (int i = 1; i < n; i++)
            M = max(M, min({z[i], i, (n - i) / 2}));

        int answerL = 0;
        for (int i = 1; i < n; i++) {
            if (i + z[i] == n && z[i] <= M)
                answerL = max(answerL, z[i]);
        }
        cout << "Case " << t << ": ";
        cout << (answerL > 0 ? s.substr(0, answerL) : "-1") << "\n";
    }

    return 0;
}