#include <iostream>
#include <algorithm>

int main()
{
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);

    long long N, M;
    std::cin >> N >> M;

    long long L = std::min(N, M) - 1;

    long long ans;
    if (N != M) ans = L * L;
    else ans = (L - 1) * (L - 1) + 1;

    std::cout << ans << '\n';
    return 0;
}