#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N;
	if (!(cin >> N)) return 0;

	vector<long long> time(N + 1);
	vector<string> S(N + 1);
	const long long NEG_INF = numeric_limits<long long>::min() / 4;

	time[0] = NEG_INF;
	S[0] = "";

	for (int i = 1; i <= N; ++i) {
		string op;
		cin >> op;

		if (op == "type") {
			char c;
			long long t;
			cin >> c >> t;
			time[i] = t;
			S[i] = S[i-1];
			S[i].push_back(c);
		} else {
			long long d, T;
			cin >> d >> T;
			time[i] = T;
			long long thr = T - d;

			auto beginIt = time.begin() + 1;
			auto endIt = time.begin() + i;
			auto it = lower_bound(beginIt, endIt, thr);
			int j = int(it - time.begin()) - 1;
			S[i] = S[j];
		}
	}

	cout << S[N] << '\n';
	return 0;
}
