#include <bits/stdc++.h>

std::string to_binary_string(unsigned int num, int n) {
    std::string binary_str;
    for (int i = n - 1; i >= 0; --i) {
        binary_str += ((num >> i) & 1) ? '1' : '0';
    }

    return binary_str;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int n;
    std::cin >> n;

    std::vector<std::string> result_set;
    unsigned int limit = 1u << n;
    for (unsigned int i : std::views::iota(0u, limit)) {
        if (std::popcount(i) % 2 == 0) {
            result_set.push_back(to_binary_string(i, n));
        }
    }

    std::cout << result_set.size() << "\n";
    for (const auto& s : result_set) {
        std::cout << s << "\n";
    }

    return 0;
}