#include <stdio.h>
#include <string.h>

#define MAXR 20
#define MAXC 20

typedef struct {
  int x, y;
} Node;

int main(void) {
  int t;
  if (scanf("%d", &t) != 1)
    return 0;

  while (t--) {
    int r, c;
    scanf("%d", &r);
    scanf("%d", &c);

    char grid[MAXR][MAXC + 1];
    for (int i = 0; i < r; i++) {
      scanf("%s", grid[i]);
    }

    if (grid[r - 1][c - 1] == '*') {
      printf("-1\n");
      continue;
    }

    int dist[MAXR][MAXC];
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        dist[i][j] = -1;
      }
    }

    Node q[MAXR * MAXC];
    int head = 0, tail = 0;

    dist[0][0] = 0;
    q[tail++] = (Node){0, 0};

    const int dx[4] = {-1, 1, 0, 0};
    const int dy[4] = {0, 0, -1, 1};

    while (head < tail) {
      Node cur = q[head++];
      int x = cur.x, y = cur.y;
      char ch = grid[x][y];

      for (int k = 0; k < 4; k++) {
        int allowed = 0;
        if (ch == '+')
          allowed = 1;
        else if (ch == '-')
          allowed = (k == 2 || k == 3);
        else if (ch == '|')
          allowed = (k == 0 || k == 1);
        else
          allowed = 0;

        if (!allowed)
          continue;

        int nx = x + dx[k];
        int ny = y + dy[k];

        if (nx < 0 || nx >= r || ny < 0 || ny >= c)
          continue;
        if (grid[nx][ny] == '*')
          continue;
        if (dist[nx][ny] != -1)
          continue;

        dist[nx][ny] = dist[x][y] + 1;
        q[tail++] = (Node){nx, ny};
      }
    }

    if (dist[r - 1][c - 1] == -1) {
      printf("-1\n");
    } else {
      printf("%d\n", dist[r - 1][c - 1] + 1);
    }
  }

  return 0;
}