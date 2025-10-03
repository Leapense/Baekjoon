#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N;
	long long s;

	if (!(cin >> N >> s)) return 0;

	vector<long long> A(N);
	for (int i = 0; i < N; ++i) cin >> A[i];
	
	sort(A.begin(), A.end());

	vector<long long> P(N + 1, 0);

	for (int i = 0; i < N; ++i) P[i + 1] = P[i] + A[i];

	long long ans;
	if (N % 2 == 0) {
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
	cout << ans << "\n";
	return 0;
}
