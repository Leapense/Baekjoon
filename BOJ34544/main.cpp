#include <bits/stdc++.h>
using namespace std;

static long long toH(long long x) {
    if (x > 0) return x;
    return x + 1;
}

static long long toFloor(long long h) {
    if (h >= 1) return h;
    return h - 1;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N;
    cin >> N;

    long long curH = toH(1);

    for (int i = 0; i < N; i++) {
        long long A, B;
        cin >> A >> B;
        curH += toH(B) - toH(A);
    }

    cout << toFloor(curH) << "\n";
    return 0;
}