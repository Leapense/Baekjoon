#include <iostream>
#include <string>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    bool has_rule[26] = {};
    bool allowed[26][26] = {};

    for (int i = 0; i < N; ++i) {
        char X;
        int M;
        cin >> X >> M;

        int x = X - 'a';
        has_rule[x] = true;

        for (int j = 0; j < M; ++j) {
            char Y;
            cin >> Y;
            allowed[x][Y - 'a'] = true;
        }
    }

    string S;
    cin >> S;

    for (int i = 0; i < (int)S.size(); ++i) {
        int x = S[i] - 'a';
        if (!has_rule[x]) continue;
        if (i == (int)S.size() - 1) continue;

        int y = S[i + 1] - 'a';
        if (!allowed[x][y]) {
            cout << "no";
            return 0;
        }
    }

    cout << "yes";
    return 0;
}