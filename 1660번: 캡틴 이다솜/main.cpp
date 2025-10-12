#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N;
	if (!(cin >> N)) return 0;

	vector<int> coin;
	for (long long k = 1;; ++k) {
		long long t = k * (k + 1) * (k + 2) / 6;
		if (t > N) break;
		coin.push_back((int)t);
	}

	const int INF = N + 5;
	vector<int> dp(N + 1, INF);
	dp[0] = 0;

	for (int p : coin) {
		for (int i = p; i <= N; ++i) {
			dp[i] = min(dp[i], dp[i - p] + 1);
		}
	}

	cout << dp[N] << '\n';
	return 0;
}
