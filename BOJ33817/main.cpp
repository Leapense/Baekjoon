#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    if (!(cin >> n)) return 0;

    if (n < 0 || n > 1000000) {
        return 1;
    }

    vector<int> x(n + 1);
    vector<int> eligible;
    eligible.reserve(n);

    for (int i = 1; i <= n; i++) {
        string s;
        int xi;
        cin >> s >> xi;
        x[i] = xi;
        if (s == "TAK") eligible.push_back(i);
    }

    vector<int> ans;
    ans.reserve(20);

    for (int i = 0; i < 10 && i < (int)eligible.size(); i++) {
        ans.push_back(eligible[i]);
    }

    for (int i = 10; i < (int)eligible.size() && (int)ans.size() < 20; i++) {
        int id = eligible[i];
        if (x[id] < 2) ans.push_back(id);
    }

    sort(ans.begin(), ans.end());

    for (size_t i = 0; i < ans.size(); i++) {
        if (i > 0) cout << ' ';
        cout << ans[i];
    }

    cout << '\n';

    return 0;
}