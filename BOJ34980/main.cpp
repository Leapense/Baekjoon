#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    string A, B;
    cin >> N >> A >> B;

    auto countW = [](const string& s) {
        return (int)count(s.begin(), s.end(), 'w');
    };

    int ca = countW(A), cb = countW(B);

    if (cb < ca) cout << "Oryang\n";
    else if (cb > ca) cout << "Manners maketh man\n";
    else {
        if (A == B) cout << "Good\n";
        else cout << "Its fine\n";
    }

    return 0;
}
