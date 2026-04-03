#include <stdio.h>
#include <string.h>

void add_small(char *s, int v) {
	int len = (int)strlen(s);
	int i = len - 1;
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
		s[i] = (char)('0' + sum);
		--i;
	}

	if (carry > 0) {
		char tmp[256];
		int p = 0;

		while (carry > 0) {
			tmp[p++] = (char)('0' + (carry % 10));
			carry /= 10;
		}

		char prefix[256];
		for (int j = 0; j < p; ++j) {
			prefix[j] = tmp[p - 1 - j];
		}

		prefix[p] = '\0';

		char result[256];
		snprintf(result, sizeof(result), "%s%s", prefix, s);
		strcpy(s, result);
	}
}

int main(void) {
	int N;
	scanf("%d", &N);

	if (N == 1) {
		printf("1906\n");
		return 0;
	}

	int k = (N - 1) / 2;
	char x[256];
	int pos = 0;

	x[pos++] = '1';
	for (int i = 0; i < k - 1; ++i) x[pos++] = '0';
	x[pos++] = '2';
	for (int i = 0; i < k - 1; ++i) x[pos++] = '0';
	x[pos++] = '1';
	x[pos] = '\0';

	add_small(x, 1905);
	printf("%s\n", x);
	return 0;
}
