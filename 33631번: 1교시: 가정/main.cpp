#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    long long F, C, E, B;
    long long Fn, Cn, En, Bn;
    int Q;

    cin >> F >> C >> E >> B;
    cin >> Fn >> Cn >> En >> Bn;
    cin >> Q;

    long long made = 0;

    while (Q--) {
        int type;
        long long i;

        cin >> type >> i;

        if (type == 1) {
            long long needF = i * Fn;
            long long needC = i * Cn;
            long long needE = i * En;
            long long needB = i * Bn;

            if (F >= needF && C >= needC && E >= needE && B >= needB) {
                F -= needF;
                C -= needC;
                E -= needE;
                B -= needB;

                made += i;
                cout << made << "\n";
            } else {
                cout << "Hello, siumii\n";
            }
        } else if (type == 2) {
            F += i;
            cout << F << "\n";
        } else if (type == 3) {
            C += i;
            cout << C << "\n";
        } else if (type == 4) {
            E += i;
            cout << E << "\n";
        } else if (type == 5) {
            B += i;
            cout << B << "\n";
        }
    }

    return 0;
}
