#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int N;
	if (scanf("%d", &N) != 1) return 0;

	int tet[130];
	int cnt = 0;

	for (long long k = 1;; ++k) {
		long long t = k * (k + 1) * (k + 2) / 6;
		if (t > N) break;
		tet[cnt++] = (int)t;
	}

	int *dp = (int *)malloc((size_t)(N + 1) * sizeof(int));
	if (!dp) return 0;
	int INF = N + 5;
	dp[0] = 0;
	for (int i = 1; i <= N; ++i) dp[i] = INF;

	for (int c = 0; c < cnt; ++c) {
		int p = tet[c];
		for (int i = p; i <= N; ++i) {
			int cand = dp[i - p] + 1;
			if (cand < dp[i]) dp[i] = cand;
		}
	}

	printf("%d\n", dp[N]);
	free(dp);
	return 0;
}
