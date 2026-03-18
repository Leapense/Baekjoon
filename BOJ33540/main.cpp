#include <iostream>
#include <map>
#include <string>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    map<string, long long> total;
    for (int i = 0; i < n; ++i) {
        string name;
        long long score;
        cin >> name >> score;
        total[name] += score;
    }

    for (const auto& [name, sum] : total) {
        cout << name << ' ' << sum << '\n';
    }

    return 0;
}