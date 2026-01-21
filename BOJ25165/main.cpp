#include <bits/stdc++.h>
using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    int Ac, D;
    int Sr, Sc;

    cin >> N >> M;
    cin >> Ac >> D;
    cin >> Sr >> Sc;

    if (Sr != N) {
        cout << "NO...\n";
        return 0; 
    }

    bool startAtM = (D == ((N % 2 == 0) ? 1 : 0));
    cout << (startAtM ? "YES!\n" : "NO...\n");
    return 0;
}