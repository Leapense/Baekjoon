#include <stdio.h>

#define MAXN 101
static const long long INF = (long long)4e18;

int main(void)
{
	int N, W, P;
	if (scanf("%d %d %d", &N, &W, &P) != 3)
		return 0;

	static long long dist[MAXN][MAXN];

	for (int i = 1; i <= N; i++)
	{
		for (int j = 1; j <= N; j++)
		{
			dist[i][j] = (i == j) ? 0 : INF;
		}
	}

	for (int i = 0; i < W; i++)
	{
		int a, b;
		int t;
		scanf("%d %d %d", &a, &b, &t);
		if ((long long)t < dist[a][b])
		{
			dist[a][b] = dist[b][a] = t;
		}
	}

	for (int k = 1; k <= N; k++)
	{
		for (int i = 1; i <= N; i++)
		{
			if (dist[i][k] == INF)
				continue;
			for (int j = 1; j <= N; j++)
			{
				if (dist[k][j] == INF)
					continue;
				long long cand = dist[i][k] + dist[k][j];
				if (cand < dist[i][j])
					dist[i][j] = cand;
			}
		}
	}

	for (int i = 0; i < P; i++)
	{
		int s, d;
		scanf("%d %d", &s, &d);
		printf("%lld\n", dist[s][d]);
	}

	return 0;
}