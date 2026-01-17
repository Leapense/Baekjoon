#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    array<bool, 5> seen{};
    for (int i = 0; i < 10; ++i) {
        int d;
        cin >> d;
        if (1 <= d && d <= 4) seen[d] = true;
    }

    int missing = 0;
    for (int lvl = 1; lvl <= 4; ++lvl) {
        if (!seen[lvl]) ++missing;
    }

    cout << missing << "\n";

    return 0;
}
