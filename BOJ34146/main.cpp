#include <iostream>
#include <vector>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int N, M;
    std::cin >> N >> M;
    
    std::vector<int> cnt(10001, 0);

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            int x;
            std::cin >> x;
            cnt[x]++;
        }
    }

    if (M % 2 == 0) {
        for (int x = 1; x <= 10000; ++x) {
            if (cnt[x] % 2 != 0) {
                std::cout << "NO";
                return 0;
            }
        }
        std::cout << "YES";
    } else {
        int odd = 0;
        for (int x = 1; x <= 10000; ++x) {
            if (cnt[x] % 2 != 0) {
                ++odd;
            }
        }

        std::cout << (odd <= N ? "YES" : "NO");
    }

    return 0;
}