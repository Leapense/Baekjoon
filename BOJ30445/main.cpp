#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

static bool isHappy(char c) {
    return c == 'H' || c == 'A' || c == 'P' || c == 'Y';
}

static bool isSad(char c) {
    return c == 'S' || c == 'A' || c == 'D';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string message;
    getline(cin, message);

    int PH = 0, PG = 0;

    for (char c : message) {
        if (isHappy(c)) ++PH;
        if (isSad(c)) ++PG;
    }

    double answer;
    if (PH == 0 && PG == 0) {
        answer = 50.0;
    } else {
        answer = 100.0 * PH / (PH + PG);
    }

    cout << fixed << setprecision(2) << answer;
    return 0;
}