#include <algorithm>
#include <array>
#include <iostream>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int n;
	std::cin >> n;

	int mn1 = 1000000000;
	int mn2 = 1000000000;
	int mn3 = 1000000000;

	for (int i = 0; i < n; ++i) {
		std::array<int, 3> a;
		std::cin >> a[0] >> a[1] >> a[2];
		std::sort(a.begin(), a.end());

		mn1 = std::min(mn1, a[0]);
		mn2 = std::min(mn2, a[1]);
		mn3 = std::min(mn3, a[2]);
	}

	long long answer = 1LL * mn1 * mn2 * mn3;
	std::cout << answer << '\n';
	return 0;
}
