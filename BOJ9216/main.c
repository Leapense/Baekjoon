#include <stdio.h>
#include <string.h>

static const char *small[] = {
	"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
	"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
	"seventeen", "eighteen", "nineteen"
};

static const char *tens_word[] = {
	"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
};

void append_part(char *dest, const char *part) {
	if (part[0] == '\0') return;
	if (dest[0] != '\0') strcat(dest, " ");
	strcat(dest, part);
}

void below_100(int n, char *out) {
	out[0] = '\0';

	if (n < 20) {
		strcpy(out, small[n]);
		return;
	}

	int t = n / 10;
	int u = n % 10;

	if (u == 0) {
		strcpy(out, tens_word[t]);
	} else {
		sprintf(out, "%s-%s", tens_word[t], small[u]);
	}
}

void triad_to_words(int n, char *out) {
	out[0] = '\0';
	if (n == 0) return;

	int h = n / 100;
	int rem = n % 100;
	char temp[64];

	if (h > 0) {
		append_part(out, small[h]);
		append_part(out, "hundred");
		if (rem > 0) {
			append_part(out, "and");
			below_100(rem, temp);
			append_part(out, temp);
		}
	} else {
		below_100(rem, temp);
		append_part(out, temp);
	}
}

int main(void) {
	int n;
	int tc = 1;

	while (scanf("%d", &n) == 1 && n != 0) {
		int million = n / 1000000;
		int thousand = (n / 1000) % 1000;
		int unit = n % 1000;

		char ans[1024];
		char temp[256];
		ans[0] = '\0';

		if (million > 0) {
			triad_to_words(million, temp);
			append_part(ans, temp);
			append_part(ans, "million");
		}

		if (thousand > 0) {
			triad_to_words(thousand, temp);
			append_part(ans, temp);
			append_part(ans, "thousand");
		}

		if (unit > 0) {
			if (ans[0] != '\0' && unit < 100) {
				append_part(ans, "and");
			}
			triad_to_words(unit, temp);
			append_part(ans, temp);
		}

		printf("Test %d: %s\n", tc++, ans);
	}
	return 0;
}
