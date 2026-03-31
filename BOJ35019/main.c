#include <stdio.h>

static void sort3(int *a, int *b, int *c) {
	int x = *a, y = *b, z = *c;
	int t;

	if (x > y) {
		t = x; x = y; y = t;
	}
	if (y > z) {
		t = y; y = z; z = t;
	}
	if (x > y) {
		t = x; x = y; y = t;
	}

	*a = x;
	*b = y;
	*c = z;
}

int main(void) {
	int n;
	scanf("%d", &n);

	int mn1 = 1000000000;
	int mn2 = 1000000000;
	int mn3 = 1000000000;

	for (int i = 0; i < n; ++i) {
		int w, h, d;
		scanf("%d %d %d", &w, &h, &d);
		sort3(&w, &h, &d);

		if (w < mn1) mn1 = w;
		if (h < mn2) mn2 = h;
		if (d < mn3) mn3 = d;
	}

	long long answer = 1LL * mn1 * mn2 * mn3;
	printf("%lld\n", answer);
	return 0;
}
