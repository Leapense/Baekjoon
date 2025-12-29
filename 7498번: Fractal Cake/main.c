#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

static int is_chocolate(int N, int64_t r, int64_t c) {
	while (N-- > 0) {
		int rm = (int)(r & 3);
		int cm = (int)(c & 3);
		if ((rm == 1 || rm == 2) && (cm == 1 || cm == 2)) return 1;
		r >>= 1;
		c >>= 1;
	}
	return 0;
}

int main(void) {
	int N;
	int64_t R1, R2, C1, C2;
	if (scanf("%d %" SCNd64 " %" SCNd64 " %" SCNd64 " %" SCNd64, &N, &R1, &R2, &C1, &C2) != 5) return 0;

	for (int64_t r = R1; r <= R2; ++r) {
		for (int64_t c = C1; c <= C2; ++c) {
			putchar(is_chocolate(N, r, c) ? '1' : '0');
		}
		putchar('\n');
	}

	return 0;
}