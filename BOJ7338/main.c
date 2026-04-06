#include <stdio.h>

typedef struct {
	int y, x1, x2;
} Horizontal;

typedef struct {
	int x, y1, y2;
} Vertical;

static void swap_int(int *a, int *b) {
	int t = *a;
	*a = *b;
	*b = t;
}

int main(void) {
	int T;
	scanf("%d", &T);

	while (T--) {
		int s;
		scanf("%d", &s);

		Horizontal hs[100];
		Vertical vs[100];
		int hcnt = 0, vcnt = 0;

		for (int i = 0; i < s; ++i) {
			int x1, y1, x2, y2;
			scanf("%d %d %d %d", &x1, &y1, &x2, &y2);

			if (y1 == y2) {
				if (x1 > x2) swap_int(&x1, &x2);
				hs[hcnt].y = y1;
				hs[hcnt].x1 = x1;
				hs[hcnt].x2 = x2;
				++hcnt;
			} else {
				if (y1 > y2) swap_int(&y1, &y2);
				vs[vcnt].x = x1;
				vs[vcnt].y1 = y1;
				vs[vcnt].y2 = y2;
				++vcnt;
			}
		}

		long long answer = 0;

		for (int i = 0; i < hcnt; ++i) {
			for (int j = i + 1; j < hcnt; ++j) {
				int y_low = hs[i].y < hs[j].y ? hs[i].y : hs[j].y;
				int y_high = hs[i].y > hs[j].y ? hs[i].y : hs[j].y;

				int left = hs[i].x1 > hs[j].x1 ? hs[i].x1 : hs[j].x1;
				int right = hs[i].x2 < hs[j].x2 ? hs[i].x2 : hs[j].x2;

				if (left > right) continue;

				int cnt = 0;
				for (int k = 0; k < vcnt; ++k) {
					if (vs[k].x >= left && vs[k].x <= right && vs[k].y1 <= y_low && vs[k].y2 >= y_high) {
						++cnt;
					}
				}

				answer += (long long)cnt * (cnt - 1) / 2;
			}
		}

		printf("%lld\n", answer);
	}
	return 0;
}
