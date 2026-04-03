#include <bits/stdc++.h>
using namespace std;

string addSmall(string s, int v) {
	int i = (int)s.size() - 1;
	int carry = v;

	while (i >= 0 && carry > 0) {
		int digit = s[i] - '0';
		int add = carry % 10;
		carry /= 10;

		int sum = digit + add;
		if (sum >= 10) {
			sum -= 10;
			carry += 1;
		}
		s[i] = char('0' + sum);
		--i;
	}

	while (carry > 0) {
		s.insert(s.begin(), char('0' + (carry % 10)));
		carry /= 10;
	}

	return s;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N;
	cin >> N;

	if (N == 1) {
		cout << 1906 << '\n';
		return 0;
	}

	int k = (N - 1) / 2;

	string x;
	x.reserve(N);
	x += '1';
	x += string(k - 1, '0');
	x += '2';
	x += string(k - 1, '0');
	x += '1';

	cout << addSmall(x, 1905) << '\n';
	return 0;
}
