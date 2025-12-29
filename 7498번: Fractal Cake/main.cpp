#include <bits/stdc++.h>
using namespace std;

static bool is_chocolate(int N, long long r, long long c) {
	while (N > 0) {
		int rm = int(r & 3);
		int cm = int(c & 3);
		if ((rm == 1 || rm == 2) && (cm == 1 || cm == 2)) return true;
		r >>= 1;
		c >>= 1;
		--N;
	}
	return false;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N;
	long long R1, R2, C1, C2;
	
	bool first_case = true;
	while (cin >> N >> R1 >> R2 >> C1 >> C2) {
		first_case = false;
		for (long long r = R1; r <= R2; ++r) {
			for (long long c = C1; c <= C2; ++c) {
				cout << (is_chocolate(N, r, c) ? '1' : '0');
			}
			cout << '\n';
		}
	}

	return 0;
}