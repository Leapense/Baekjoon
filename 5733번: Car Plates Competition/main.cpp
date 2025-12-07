#include <iostream>
#include <string>
#include <vector>
#include <cctype>
#include <cmath>
#include <optional>
#include <algorithm>
#include <map>

constexpr long long OLD_SCHEME_LIMIT = 175760000LL;

class PlateSolver {
    private:
    std::vector<int> new_char_map;

    public:
    PlateSolver() : new_char_map(26, -1) {
        const std::string invalid = "ACMIP";
        int valid_idx = 0;
        for (int i = 0; i < 26; ++i) {
            char c = 'A' + i;
            if (invalid.find(c) == std::string::npos) {
                new_char_map[i] = valid_idx++;
            }
        }
    }

    std::optional<long long> get_value(const std::string& s) const {
        if (s.length() != 7) return std::nullopt;

        // 공통: 0~2 문자, 5~6 숫자 체크
        if (!std::all_of(s.begin(), s.begin() + 3, [](unsigned char c){ return std::isupper(c); })) 
            return std::nullopt;
        if (!std::all_of(s.begin() + 5, s.end(), [](unsigned char c){ return std::isdigit(c); })) 
            return std::nullopt;

        // 4번째(인덱스 3) 문자로 구형/신형 판별
        if (std::isdigit(s[3])) {
            // [OLD] LLL DDDD
            if (!std::isdigit(s[4])) return std::nullopt;

            long long letters = 0;
            for (int i = 0; i < 3; ++i) {
                letters = letters * 26 + (s[i] - 'A');
            }
            long long digits = std::stoll(s.substr(3));
            
            return letters * 10000 + digits;

        } else if (std::isupper(s[3])) {
            // [NEW] LLLLL DD
            if (!std::isupper(s[4])) return std::nullopt;

            long long letters = 0;
            for (int i = 0; i < 5; ++i) {
                int val = new_char_map[s[i] - 'A'];
                if (val == -1) return std::nullopt; // 금지된 문자
                letters = letters * 21 + val;
            }
            long long digits = std::stoll(s.substr(5));

            return OLD_SCHEME_LIMIT + (letters * 100 + digits);
        }

        return std::nullopt;
    }
};

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    PlateSolver solver;
    std::string sm, si;
    long long c;

    while (std::cin >> sm >> si >> c) {
        if (sm == "*" && si == "*" && c == 0) break;

        auto val_m_opt = solver.get_value(sm);
        auto val_i_opt = solver.get_value(si);

        long long val_m = val_m_opt.value_or(0);
        bool win = false;
        if (val_i_opt.has_value()) {
            long long val_i = val_i_opt.value();
            if (val_i > val_m && val_i <= val_m + c) {
                win = true;
            }
        }

        std::cout << (win ? "Y" : "N") << "\n";
    }

    return 0;
}