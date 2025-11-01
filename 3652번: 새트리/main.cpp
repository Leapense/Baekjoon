#include <bits/stdc++.h>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::string s;
    if (!(std::cin >> s)) return 0;

    long long a = 0, b = 0;
    {
        auto pos = s.find('/');
        a = std::stoll(s.substr(0, pos));
        b = std::stoll(s.substr(pos + 1));
    }

    std::string path_rev;
    path_rev.reserve(10000);

    while (!(a == 1 && b == 1)) {
        if (a < b) {
            long long k = (b - 1) / a;
            path_rev.append((size_t)k, 'R');
            b -= a * k;
        } else {
            long long k = (a - 1) / b;
            path_rev.append((size_t)k, 'L');
            a -= b * k;
        }
    }

    std::reverse(path_rev.begin(), path_rev.end());
    std::cout << path_rev << "\n";
    return 0;
}