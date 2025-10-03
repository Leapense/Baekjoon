#include <stdio.h>
#include <stdlib.h>

static int cmp_ll(const void *a, const void *b) {
	long long x = *(const long long*)a;
	long long y = *(const long long*)b;
	return (x > y) - (x < y);
}

int main(void) {
	int N;
	long long s;
	if (scanf("%d %lld", &N, &s) != 2) return 0;

	long long *A = (long long*)malloc(sizeof(long long) * (size_t)N);
	for (int i = 0; i < N; ++i) scanf("%lld", &A[i]);
	
	qsort(A, (size_t)N, sizeof(long long), cmp_ll);

	long long *P = (long long*)malloc(sizeof(long long) * (size_t)(N + 1));
	P[0] = 0;
	for (int i = 0; i < N; ++i) P[i + 1] = P[i] + A[i];

	long long ans;
	if ((N & 1) == 0) {
		int k = N / 2;
		long long sumPos = P[N] - P[N - k];
		long long sumNeg = P[k];
		ans = s + 2 * (sumPos - sumNeg);
	} else {
		int k = N / 2;
		long long sumPos = P[N] - P[N - (k + 1)];
		long long sumNeg = P[k];
		ans = -s + 2 * (sumPos - sumNeg);
	}

	printf("%lld\n", ans);
	free(P);
	free(A);
	return 0;
}
