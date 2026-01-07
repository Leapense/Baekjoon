#include <stdio.h>
#include <stdbool.h>

#define MAXN 1000000

int main(void) {
	int T;
	if (scanf("%d", &T) != 1) return 0;

	static bool isSquare[MAXN + 1] = { false };

	for (int i = 0; i * i <= MAXN; i++) {
		isSquare[i * i] = true;
	}

	while (T--) {
		int N;
		scanf("%d", &N);

		bool odd = (N & 1);
		bool sq = isSquare[N];

		if (odd && sq) puts("OS");
		else if (odd) puts("O");
		else if (sq) puts("S");
		else puts("EMPTY");
	}


	return 0;
}