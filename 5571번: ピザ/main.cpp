#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <print>

using namespace std;

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    long long d;
    int n, m;

    if (!(std::cin >> d >> n >> m)) return 0;

    std::vector<long long> stores;
    stores.reserve(n);
    stores.push_back(0);

    for (int i = 0; i < n - 1; ++i) {
        long long pos;
        std::cin >> pos;
        stores.push_back(pos);
    }

    std::sort(stores.begin(), stores.end());

    long long total_dist = 0;
    for (int i = 0; i < m; ++i) {
        long long k;
        std::cin >> k;
        auto it = std::lower_bound(stores.begin(), stores.end(), k);
        long long dist = 0;
        if (it == stores.end()) {
            long long right_dist = d - k;
            long long left_dist = k - stores.back();
            dist = std::min(right_dist, left_dist);
        } else {
            long long right_dist = *it - k;
            long long left_dist;
            if (it == stores.begin()) {
                left_dist = k + (d - stores.back());
            } else {
                left_dist = k - *std::prev(it);
            }
            dist = std::min(right_dist, left_dist);
        }

        total_dist += dist;
    }

    std::cout << total_dist << "\n";
    return 0;
}