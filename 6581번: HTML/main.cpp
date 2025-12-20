#include <iostream>
#include <string>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    
    std::string tok;
    int line_len = 0;
    
    while (std::cin >> tok) {
        if (tok == "<br>") {
            std::cout << '\n';
            line_len = 0;
        } else if (tok == "<hr>") {
            if (line_len != 0) std::cout << '\n';
            std::cout << std::string(80, '-') << '\n';
            line_len = 0;
        } else {
            int wlen = static_cast<int>(tok.size());
            if (line_len == 0) {
                std::cout << tok;
                line_len = wlen;
            } else {
                if (line_len + 1 + wlen <= 80) {
                    std::cout << ' ' << tok;
                    line_len += 1 + wlen;
                } else {
                    std::cout << '\n' << tok;
                    line_len = wlen;
                }
            }
        }
    }
    
    std::cout << '\n';
    return 0;
}