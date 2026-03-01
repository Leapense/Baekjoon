#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string s;
    getline(cin, s);

    const string qwerty = "qwertyuiopasdfghjklzxcvbnm";

    for (char ch : s) {
        if (ch == ' ') cout << ' ';
        else cout << qwerty[ch - 'a'];
    }

    return 0;
}