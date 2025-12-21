#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int t;
  cin >> t;

  while (t--) {
    int r, c;
    cin >> r >> c;

    vector<string> grid(r);
    for (int i = 0; i < r; i++)
      cin >> grid[i];

    if (grid[r - 1][c - 1] == '*') {
      cout << -1 << "\n";
      continue;
    }

    vector<vector<int>> dist(r, vector<int>(c, -1));
    queue<pair<int, int>> q;

    dist[0][0] = 0;
    q.push({0, 0});

    const int dx[4] = {-1, 1, 0, 0};
    const int dy[4] = {0, 0, -1, 1};

    auto allowed = [&](char ch, int dir) -> bool {
      if (ch == '+')
        return true;
      if (ch == '-')
        return (dir == 2 || dir == 3);
      if (ch == '|')
        return (dir == 0 || dir == 1);
      return false;
    };

    while (!q.empty()) {
      auto [x, y] = q.front();
      q.pop();
      char ch = grid[x][y];

      for (int dir = 0; dir < 4; dir++) {
        if (!allowed(ch, dir))
          continue;

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (nx < 0 || nx >= r || ny < 0 || ny >= c)
          continue;
        if (grid[nx][ny] == '*')
          continue;
        if (dist[nx][ny] != -1)
          continue;

        dist[nx][ny] = dist[x][y] + 1;
        q.push({nx, ny});
      }
    }

    if (dist[r - 1][c - 1] == -1)
      cout << -1 << "\n";
    else
      cout << (dist[r - 1][c - 1] + 1) << "\n";
  }

  return 0;
}