#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

int main(void) {
	int n, m, k;
	while (scanf("%d %d %d", &n, &m, &k) == 3) {
		if (n == 0 && m == 0 && k == 0) break;

		int maxSum = n * m;
		uint64_t *cur = (uint64_t*)calloc((size_t)(maxSum + 1), sizeof(uint64_t));
		uint64_t *nxt = (uint64_t*)calloc((size_t)(maxSum + 1), sizeof(uint64_t));

		if (!cur || !nxt) return 0;

		cur[0] = 1;
		for (int i = 1; i <= n; ++i) {
			for (int s = 0; s <= maxSum; ++s) nxt[s] = 0;
			for (int s = 0; s <= maxSum; ++s) {
				uint64_t ways = cur[s];
				if (!ways) continue;
				int tmin = s + 1;
				int tmax = (s + m <= maxSum) ? (s + m) : maxSum;
				for (int t = tmin; t <= tmax; ++t) nxt[t] += ways;
			}
			uint64_t *tmp = cur;
			cur = nxt;
			nxt = tmp;
		}
		long double total = 1.0L;
		for (int i = 0; i < n; ++i) total *= (long double)m;

		long double acc = 0.0L;
		for (int s = n; s <= maxSum; ++s) {
			long double payout = (s - k >= 1) ? (long double)(s - k) : 1.0L;
			acc += payout * (long double)cur[s];
		}
		long double ans = acc / total;

		printf("%.8Lf\n", ans);

		free(cur);
		free(nxt);
	}
	return 0;
}
