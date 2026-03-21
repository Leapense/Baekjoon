#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	string s;
	cin >> s;

	const string order = "ROYGP";
	array<long long, 5> cnt{};

	auto idx = [&](char c) -> int {
		if (c == 'R') return 0;
		if (c == 'O') return 1;
		if (c == 'Y') return 2;
		if (c == 'G') return 3;
		return 4;
	};

	for (char c : s) {
		++cnt[idx(c)];
	}

	for (int k = 5; k >= 1; --k) {
		vector<pair<long long, int>> v;
		for (int i = 0; i < 5; ++i) {
			v.push_back({cnt[i], i});
		}

		sort(v.begin(), v.end(), [](const auto& a, const auto& b) {
			if (a.first != b.first) return a.first > b.first;
			return a.second < b.second;
		});

		long long batches = v[k - 1].first;

		if (batches == 0) {
			cout << 0 << '\n';
			continue;
		}

		vector<int> chosen;
		for (int i = 0; i < k; ++i) {
			chosen.push_back(v[i].second);
		}
		sort(chosen.begin(), chosen.end());
		string batch_colors;
		for (int id : chosen) {
			batch_colors += order[id];
			cnt[id] -= batches;
		}

		cout << batches << ' ' << batch_colors << '\n';
	}

	return 0;
}