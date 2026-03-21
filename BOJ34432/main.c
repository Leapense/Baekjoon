#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef long long ll;

typedef struct
{
	ll cnt;
	int idx;
} Pair;

static int color_index(char c)
{
	if (c == 'R')
		return 0;
	if (c == 'O')
		return 1;
	if (c == 'Y')
		return 2;
	if (c == 'G')
		return 3;
	return 4;
}

static int cmp_pair(const void *a, const void *b)
{
	const Pair *x = (const Pair *)a;
	const Pair *y = (const Pair *)b;

	if (x->cnt != y->cnt)
	{
		return (x->cnt < y->cnt) ? 1 : -1;
	}
	return x->idx - y->idx;
}

static int cmp_int(const void *a, const void *b)
{
	int x = *(const int *)a;
	int y = *(const int *)b;
	return x - y;
}

int main(void)
{
	static char s[1000005];
	const char order[6] = "ROYGP";
	ll cnt[5] = {0, 0, 0, 0, 0};

	if (scanf("%1000000s", s) != 1)
	{
		return 0;
	}

	for (int i = 0; s[i] != '\0'; ++i)
	{
		++cnt[color_index(s[i])];
	}

	for (int k = 5; k >= 1; --k)
	{
		Pair v[5];
		for (int i = 0; i < 5; ++i)
		{
			v[i].cnt = cnt[i];
			v[i].idx = i;
		}

		qsort(v, 5, sizeof(Pair), cmp_pair);

		ll batches = v[k - 1].cnt;
		if (batches == 0)
		{
			printf("0\n");
			continue;
		}

		int chosen[5];
		for (int i = 0; i < k; ++i)
		{
			chosen[i] = v[i].idx;
		}

		qsort(chosen, k, sizeof(int), cmp_int);

		char batch_colors[6];

		for (int i = 0; i < k; ++i)
		{
			batch_colors[i] = order[chosen[i]];
			cnt[chosen[i]] -= batches;
		}
		batch_colors[k] = '\0';
		printf("%lld %s\n", batches, batch_colors);
	}

	return 0;
}