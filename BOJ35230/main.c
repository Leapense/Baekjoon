#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n;
	if (scanf("%d", &n) != 1) return 0;

	int *cnt = (int *)calloc((size_t)(n + 1), sizeof(int));
	if (cnt == NULL) return 0;

	for (int i = 0; i < n; ++i) {
		int a;
		scanf("%d", &a);
		cnt[a]++;
	}

	int answer = 0;
	for (int x = 0; x <= n; ++x) {
		if (cnt[x] == 0) continue;

		if (cnt[x] < x) {
			answer += cnt[x];
		} else {
			answer += cnt[x] - x;
		}
	}

	printf("%d\n", answer);

	free(cnt);
	return 0;
}
