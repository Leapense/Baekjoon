#include <bits/stdc++.h>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;

    string A, K, B, M;
    cin >> A >> K >> B >> M;

    string out;
    out.reserve(N);

    for (int i = 0; i < N; i++) {
        if (A[i] == B[i]) {
            if (K[i] != M[i]) {
                cout << "htg!\n";
                return 0;
            }
            out.push_back(K[i]);
        }
    }

    cout << out << "\n";
    return 0;
}