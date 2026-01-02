#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int A, B, C, D;
    cin >> A >> B >> C >> D;

    bool shuttle_ok = (A + B <= D);
    bool walk_ok = (C <= D);

    if (shuttle_ok && walk_ok) cout << "~.~\n";
    else if (!shuttle_ok && !walk_ok) cout << "T.T\n";
    else if (shuttle_ok) cout << "Shuttle\n";
    else cout << "Walk\n";

    return 0;
}