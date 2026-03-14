#include <algorithm>
#include <iostream>
#include <vector>

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int N;
    std::cin >> N;

    std::vector<int> a(N);
    for (int i = 0; i < N; ++i) {
        std::cin >> a[i];
    }

    std::sort(a.begin(), a.end());
    std::vector<int> ans;

    int prev = a[0];
    for (int i = 1; i < N; ++i) {
        if (a[i] == prev) continue;

        for (int x = prev + 1; x < a[i]; ++x) {
            ans.push_back(x);
        }

        prev = a[i];
    }

    std::cout << ans.size() << '\n';
    for (std::size_t i = 0; i < ans.size(); ++i) {
        if (i) std::cout << ' ';
        std::cout << ans[i];
    }

    std::cout << '\n';
    return 0;
}