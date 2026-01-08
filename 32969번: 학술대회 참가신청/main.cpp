#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string s;
    getline(cin, s);

    for (char &c : s) c = (char)tolower((unsigned char)c);
    vector<string> digital = {"social", "history", "language", "literacy"};
    vector<string> public_bd = {"bigdata", "public", "society"};

    for (const auto& w : digital) {
        if (s.find(w) != string::npos) {
            cout << "digital humanities\n";
            return 0;
        }
    }

    for (const auto& w : public_bd) {
        if (s.find(w) != string::npos) {
            cout << "public bigdata\n";
            return 0;
        }
    }

    return 0;
}
