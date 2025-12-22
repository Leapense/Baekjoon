#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static char *dupstr(const char *s) {
  size_t len = strlen(s);
  char *p = (char *)malloc(len + 1);
  if (!p)
    exit(1);
  memcpy(p, s, len + 1);
  return p;
}

static int cmp_strptr_qsort(const void *a, const void *b) {
  const char *sa = *(const char *const *)a;
  const char *sb = *(const char *const *)b;
  return strcmp(sa, sb);
}

static int cmp_key_elem_bsearch(const void *key, const void *elem) {
  const char *k = *(const char *const *)key;
  const char *e = *(const char *const *)elem;
  return strcmp(k, e);
}

static int get_id(const char *s, char **uniq, int m) {
  const char *key = s;
  char **found = (char **)bsearch(&key, uniq, (size_t)m, sizeof(char *),
                                  cmp_key_elem_bsearch);
  return (int)(found - uniq);
}

int main(void) {
  int L;
  if (scanf("%d", &L) != 1)
    return 0;
  while (L--) {
    int n;
    scanf("%d", &n);
    if (n == 0) {
      printf("0\n");
      continue;
    }

    char **names = (char **)malloc((size_t)n * sizeof(char *));
    char **sorted = (char **)malloc((size_t)n * sizeof(char *));

    if (!names || !sorted)
      exit(1);

    char buf[256];
    for (int i = 0; i < n; i++) {
      scanf("%255s", buf);
      names[i] = dupstr(buf);
      sorted[i] = names[i];
    }

    char *root_name = names[n - 1];
    qsort(sorted, (size_t)n, sizeof(char *), cmp_strptr_qsort);

    char **uniq = (char **)malloc((size_t)n * sizeof(char *));
    if (!uniq)
      exit(1);
    int m = 0;
    for (int i = 0; i < n; i++) {
      if (i == 0 || strcmp(sorted[i], sorted[i - 1]) != 0) {
        uniq[m++] = sorted[i];
      }
    }

    int root_id = get_id(root_name, uniq, m);
    int *node_stack = (int *)malloc((size_t)(m + 1) * sizeof(int));
    int *max_stack = (int *)malloc((size_t)(m + 1) * sizeof(int));
    int *cnt_stack = (int *)malloc((size_t)(m + 1) * sizeof(int));
    if (!node_stack || !max_stack || !cnt_stack)
      exit(1);

    int sp = 1;
    node_stack[0] = root_id;
    max_stack[0] = 0;
    cnt_stack[0] = 0;

    for (int i = 0; i < n; i++) {
      int x = get_id(names[i], uniq, m);
      if (sp >= 2 && x == node_stack[sp - 2]) {
        int child_max = max_stack[sp - 1];
        int child_cnt = cnt_stack[sp - 1];
        sp--;

        int S_child = (child_cnt == 0) ? 1 : (2 + child_max);
        cnt_stack[sp - 1] += 1;
        if (S_child > max_stack[sp - 1]) {
          max_stack[sp - 1] = S_child;
        }
      } else {
        node_stack[sp] = x;
        max_stack[sp] = 0;
        cnt_stack[sp] = 0;
        sp++;
      }
    }

    long long new_units =
        (cnt_stack[0] == 0) ? 0LL : (1LL + (long long)max_stack[0]);
    long long old_units = (long long)n;
    long long saved_seconds = (old_units - new_units) * 10LL;

    printf("%lld\n", saved_seconds);

    for (int i = 0; i < n; i++)
      free(names[i]);
    free(names);
    free(sorted);
    free(uniq);
    free(node_stack);
    free(max_stack);
    free(cnt_stack);
  }

  return 0;
}