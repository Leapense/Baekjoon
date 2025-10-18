#include <stdio.h>
#include <limits.h>

int main(void) {
	int N;
	if (scanf("%d", &N) != 1) return 0;
	int A[1000 + 1];
	for (int i = 1; i <= N; ++i) scanf("%d", &A[i]);
	int dp[1000 + 1] = {0};

	for (int i = 1; i <= N; ++i) {
		int curMin = A[i], curMax = A[i];
		int best = dp[i - 1];
		for (int j = i; j >= 1; --j) {
			if (A[j] < curMin) curMin = A[j];
			if (A[j] > curMax) curMax = A[j];
			int cand = dp[j - 1] + (curMax - curMin);
			if (cand > best) best = cand;
		}
		dp[i] = best;
	}
	printf("%d\n", dp[N]);
	return 0;
}
