#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int x, y, z;
    cin >> x >> y >> z;

    int n = x + y + 2 * z;

    char grade;
    if (n >= 360) grade = 'A';
    else if (n >= 320) grade = 'B';
    else if (n >= 280) grade = 'C';
    else if (n >= 240) grade = 'D';
    else grade = 'F';

    cout << grade << "\n";
    return 0;
}