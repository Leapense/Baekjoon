#include <iostream>
#include <vector>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int n;
	cin >> n;

	vector<int> cnt(n + 1, 0);

	for (int i = 0; i < n; ++i) {
		int a;
		cin >> a;
		++cnt[a];
	}

	int answer = 0;
	for (int x = 0; x <= n; ++x) {
		if (cnt[x] == 0) continue;
		if (cnt[x] < x) {
			answer += cnt[x];
		} else {
			answer += cnt[x] - x;
		}
	}

	cout << answer << "\n";
	return 0;
}
