#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static void die(void) {
  fprintf(stderr, "Memory allocation failed\n");
  exit(1);
}

int main(void) {
  int n, m;
  if (scanf("%d %d", &n, &m) != 2)
    return 0;

  int *U = (int *)malloc((size_t)m * sizeof(int));
  int *V = (int *)malloc((size_t)m * sizeof(int));
  int *W = (int *)malloc((size_t)m * sizeof(int));

  if (!U || !V || !W)
    die();
  for (int i = 0; i < m; i++) {
    if (scanf("%d %d %d", &U[i], &V[i], &W[i]) != 3)
      return 0;
  }

  int k;
  if (scanf("%d", &k) != 1)
    return 0;

  int *head = (int *)malloc((size_t)(n + 1) * sizeof(int));
  int *to = (int *)malloc((size_t)(2 * m) * sizeof(int));
  int *nxt = (int *)malloc((size_t)(2 * m) * sizeof(int));
  if (!head || !to || !nxt)
    die();

  for (int i = 1; i <= n; i++)
    head[i] = -1;

  int idx = 0;
  for (int i = 0; i < m; i++) {
    if (W[i] >= k) {
      int a = U[i], b = V[i];
      to[idx] = b;
      nxt[idx] = head[a];
      head[a] = idx;
      idx++;
      to[idx] = a;
      nxt[idx] = head[b];
      head[b] = idx;
      idx++;
    }
  }

  unsigned char *vis = (unsigned char *)calloc((size_t)(n + 1), 1);
  int *stack = (int *)malloc((size_t)n * sizeof(int));
  if (!vis || !stack)
    die();

  int components = 0;

  for (int s = 1; s <= n; s++) {
    if (vis[s])
      continue;
    components++;

    int top = 0;
    stack[top++] = s;
    vis[s] = 1;

    while (top > 0) {
      int x = stack[--top];
      for (int e = head[x]; e != -1; e = nxt[e]) {
        int y = to[e];
        if (!vis[y]) {
          vis[y] = 1;
          stack[top++] = y;
        }
      }
    }
  }

  int answer = components - 1;
  if (answer < 0)
    answer = 0;
  printf("%d\n", answer);

  free(U);
  free(V);
  free(W);
  free(head);
  free(to);
  free(nxt);
  free(vis);
  free(stack);
  return 0;
}