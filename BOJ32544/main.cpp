#include <iostream>
#include <cstdint>

uint64_t calculateMaxPyramidHeight(uint64_t totalPeople) {
    auto canFormPyramid = [totalPeople](uint64_t height) -> bool {
        unsigned __int128 requiredPeople = (static_cast<unsigned __int128>(height) * (height + 1)) / 2;
        return requiredPeople <= totalPeople;
    };
    uint64_t minHeight = 0;
    uint64_t maxHeight = 60740010000ULL;

    while (minHeight < maxHeight) {
        uint64_t midHeight = minHeight + (maxHeight - minHeight + 1) / 2;

        if (canFormPyramid(midHeight)) {
            minHeight = midHeight;
        } else {
            maxHeight = midHeight - 1;
        }
    }

    return minHeight;
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    uint64_t totalPeople;
    if (std::cin >> totalPeople) {
        std::cout << calculateMaxPyramidHeight(totalPeople);
    }
    return 0;
}