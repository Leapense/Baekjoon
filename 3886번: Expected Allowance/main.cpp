#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int n, m, k;
	cout.setf(std::ios::fixed);
	cout << setprecision(8);
	while ((cin >> n >> m >> k)) {
		if (n == 0 && m == 0 && k == 0) break;
		int maxSum = n * m;
		vector<uint64_t> cur(maxSum + 1, 0), nxt(maxSum + 1, 0);
		cur[0] = 1;

		for (int i = 1; i <= n; ++i) {
			fill(nxt.begin(), nxt.end(), 0);
			for (int s = 0; s <= maxSum; ++s) {
				uint64_t ways = cur[s];
				if (!ways) continue;
				int tmin = s + 1;
				int tmax = min(s + m, maxSum);
				for (int t = tmin; t <= tmax; ++t) nxt[t] += ways;
			}
			swap(cur, nxt);
		}

		long double total = 1.0L;
		for (int i = 0; i < n; ++i) total *= (long double)m;

		long double acc = 0.0L;
		for (int s = n; s <= maxSum; ++s) {
			long double payout = (s - k >= 1) ? (long double)(s - k) : 1.0L;
			acc += payout * (long double)cur[s];
		}
		long double ans = acc / total;
		cout << (double)ans << "\n";
	}
	return 0;
}
