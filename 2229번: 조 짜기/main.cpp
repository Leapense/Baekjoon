#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	
	int N;
	if (!(cin >> N)) return 0;

	vector<int> A(N + 1);
	for (int i = 1; i <= N; ++i) cin >> A[i];

	vector<int> dp(N + 1, 0);

	for (int i = 1; i <= N; ++i) {
		int curMin = A[i], curMax = A[i];
		int best = dp[i - 1];
		for (int j = i; j >= 1; --j) {
			curMin = min(curMin, A[j]);
			curMax = max(curMax, A[j]);
			best = max(best, dp[j - 1] + (curMax - curMin));
		}
		dp[i] = best;
	}
	cout << dp[N] << "\n";
	return 0;
}
