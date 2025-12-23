#include <bits/stdc++.h>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int N, W, P;
	cin >> N >> W >> P;

	const long long INF = (long long)4e18;
	vector<vector<long long>> dist(N + 1, vector<long long>(N + 1, INF));
	for (int i = 1; i <= N; ++i)
		dist[i][i] = 0;
	for (int i = 0; i < W; ++i)
	{
		int a, b, t;
		cin >> a >> b >> t;
		dist[a][b] = min(dist[a][b], (long long)t);
		dist[b][a] = min(dist[b][a], (long long)t);
	}

	for (int k = 1; k <= N; ++k)
	{
		for (int i = 1; i <= N; ++i)
		{
			if (dist[i][k] == INF)
				continue;
			for (int j = 1; j <= N; ++j)
			{
				if (dist[k][j] == INF)
					continue;
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}

	while (P--)
	{
		int s, d;
		cin >> s >> d;
		cout << dist[s][d] << "\n";
	}

	return 0;
}