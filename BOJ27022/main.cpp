#include <iostream>
#include <set>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int D;
    cin >> D;

    long long x = 0, y = 0;
    set<long long> vertical;
    set<long long> horizontal;

    for (int i = 0; i < D; ++i) {
        char dir;
        long long dist;
        cin >> dir >> dist;

        if (dir == 'N') {
            vertical.insert(x);
            y += dist;
        } else if (dir == 'S') {
            vertical.insert(x);
            y -= dist;
        } else if (dir == 'E') {
            horizontal.insert(y);
            x += dist;
        } else if (dir == 'W') {
            horizontal.insert(y);
            x -= dist;
        }
    }

    cout << vertical.size() + horizontal.size();
    return 0;
}