#include <iostream>
#include <vector>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int T;
    std::cin >> T;

    while (T--) {
        int N;
        std::cin >> N;

        std::vector<long long> speed(N);
        for (int i = 0; i < N; i++) {
            std::cin >> speed[i];
        }

        long long blocked = 0;
        long long ans = 0;

        for (int i = 0; i < N; ++i) {
            char c;
            std::cin >> c;

            if (c == 'A') {
                blocked += speed[i];
            } else {
                if (blocked >= speed[i]) {
                    blocked -= speed[i];
                } else {
                    ans += speed[i] - blocked;
                    blocked = 0;
                }
            }
        }

        std::cout << ans << '\n';
    }

    return 0;
}