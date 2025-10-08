#include <stdio.h>
#include <string.h>
#include <limits.h>

int main(void) {
	int N;
	if (scanf("%d", &N) != 1) return 0;

	long long time[55];
	char S[55][55];
	const long long NEG_INF = LLONG_MIN / 4;

	time[0] = NEG_INF;
	S[0][0] = '\0';

	for (int i = 1; i <= N; ++i) {
		char op[8];
		scanf("%s", op);
		if (op[0] == 't') {
			char c;
			long long t;
			scanf(" %c %lld", &c, &t);
			time[i] = t;
			strcpy(S[i], S[i - 1]);
			size_t len = strlen(S[i]);
			S[i][len] = c;
			S[i][len + 1] = '\0';
		} else {
			long long d, T;
			scanf(" %lld %lld", &d, &T);
			time[i] = T;
			long long thr = T - d;

			int j = 0;
			for (int k = 1; k <= i - 1; ++k) {
				if (time[k] < thr) j = k;
				else break;
			}
			strcpy(S[i], S[j]);
		}
	}

	printf("%s\n", S[N]);
	return 0;
}
