#include <bits/stdc++.h>
using namespace std;

static const vector<string> small = {
	"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
	"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
	"seventeen", "eighteen", "nineteen"
};

static const vector<string> tens_word = {
	"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
};

string below_100(int n) {
	if (n < 20) return small[n];
	int t = n / 10;
	int u = n % 10;
	if (u == 0) return tens_word[t];
	return tens_word[t] + "-" + small[u];
}

string triad_to_words(int n) {
	if (n == 0) return "";

	string res;
	int h = n / 100;
	int rem = n % 100;

	if (h > 0) {
		res += small[h] + " hundred";
		if (rem > 0) {
			res += " and " + below_100(rem);
		}
	} else {
		res += below_100(rem);
	}
	return res;
}

void append_part(string &res, const string &part) {
	if (part.empty()) return;
	if (!res.empty()) res += ' ';
	res += part;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int n;
	int tc = 1;

	while (cin >> n && n != 0) {
		int million = n / 1'000'000;
		int thousand = (n / 1'000) % 1'000;
		int unit = n % 1'000;

		string ans;

		if (million > 0) {
			append_part(ans, triad_to_words(million));
			append_part(ans, "million");
		}

		if (thousand > 0) {
			append_part(ans, triad_to_words(thousand));
			append_part(ans, "thousand");
		}

		if (unit > 0) {
			if (!ans.empty() && unit < 100) {
				append_part(ans, "and");
			}
			append_part(ans, triad_to_words(unit));
		}
		cout << "Test " << tc++ << ": " << ans << '\n';
	}

	return 0;
}
