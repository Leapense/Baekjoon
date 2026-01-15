#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int A, B;
    cin >> A >> B;

    string out;
    bool hasX = false;

    if (A != 0) {
        if (A == 1) out += "x";
        else if (A == -1) out += "-x";
        else out += to_string(A) + "x";
        hasX = true;
    }

    if (B != 0) {
        if (hasX) {
            if (B > 0) out += "+" + to_string(B);
            else out += to_string(B);
        } else {
            out += to_string(B);
        }
    }

    if (!hasX && B == 0) out = "0";

    cout << out;
    return 0;
}