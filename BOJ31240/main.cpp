#include <iostream>
#include <string>
#include <string_view>

long long count_valid_splits(std::string_view s) {
    const auto n_total = s.size();
    if (n_total < 2 || s[0] == '0') {
        return 0;
    }

    long long count = 0;
    const size_t max_k = n_total / 2;

    for (size_t k = 1; k <= max_k; ++k) {
        if (s[k] == '0') {
            continue;
        }

        const size_t m_len = k;
        const size_t n_len = n_total - k;

        if (m_len < n_len) {
            count++;
        } else if (m_len == n_len) {
            std::string_view m_val = s.substr(0, k);
            std::string_view n_val = s.substr(k, k);
            if (m_val <= n_val) {
                count++;
            }
        }
    }

    return count;
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::string input;
    if (!(std::cin >> input)) {
        return 0;
    }

    std::cout << count_valid_splits(input) << "\n";
    return 0;
}