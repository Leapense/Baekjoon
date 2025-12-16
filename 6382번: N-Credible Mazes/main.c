#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
  int *p;
  unsigned char *r;
  int n, cap;
} DSU;

static void dsu_init(DSU *d) {
  d->n = 0;
  d->cap = 16;
  d->p = (int *)malloc(sizeof(int) * d->cap);
  d->r = (unsigned char *)malloc(sizeof(unsigned char) * d->cap);
}

static void dsu_free(DSU *d) {
  free(d->p);
  free(d->r);
}

static int dsu_add(DSU *d) {
  if (d->n == d->cap) {
    d->cap *= 2;
    d->p = (int *)realloc(d->p, sizeof(int) * d->cap);
    d->r = (unsigned char *)realloc(d->r, sizeof(unsigned char) * d->cap);
  }
  int id = d->n++;
  d->p[id] = id;
  d->r[id] = 0;
  return id;
}

static int dsu_find(DSU *d, int x) {
  while (d->p[x] != x) {
    d->p[x] = d->p[d->p[x]];
    x = d->p[x];
  }

  return x;
}

static void dsu_unite(DSU *d, int a, int b) {
  a = dsu_find(d, a);
  b = dsu_find(d, b);

  if (a == b)
    return;
  if (d->r[a] < d->r[b]) {
    int t = a;
    a = b;
    b = t;
  }
  d->p[b] = a;
  if (d->r[a] == d->r[b])
    d->r[a]++;
}

typedef struct {
  uint64_t *keys;
  int *vals;
  unsigned char *used;
  size_t cap;
  size_t size;
} HMap;

static uint64_t splitmix64(uint64_t x) {
  x += 0x9e3779b97f4a7c15ULL;
  x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9ULL;
  x = (x ^ (x >> 27)) * 0x94d049bb133111ebULL;
  return x ^ (x >> 31);
}

static void hmap_init(HMap *m, size_t cap_pow2) {
  m->cap = cap_pow2;
  m->size = 0;
  m->keys = (uint64_t *)malloc(sizeof(uint64_t) * m->cap);
  m->vals = (int *)malloc(sizeof(int) * m->cap);
  m->used = (unsigned char *)malloc(sizeof(unsigned char) * m->cap);
  memset(m->used, 0, m->cap);
}

static void hmap_free(HMap *m) {
  free(m->keys);
  free(m->vals);
  free(m->used);
}

static void hmap_rehash(HMap *m, size_t newcap) {
  HMap nm;
  hmap_init(&nm, newcap);

  for (size_t i = 0; i < m->cap; i++) {
    if (!m->used[i])
      continue;
    uint64_t key = m->keys[i];
    int val = m->vals[i];
    size_t mask = nm.cap - 1;
    size_t pos = (size_t)splitmix64(key) & mask;
    while (nm.used[pos] && nm.keys[pos] != key)
      pos = (pos + 1) & mask;
    nm.used[pos] = 1;
    nm.keys[pos] = key;
    nm.vals[pos] = val;
    nm.size++;
  }

  hmap_free(m);
  *m = nm;
}

static int hmap_get_or_add(HMap *m, DSU *dsu, uint64_t key) {
  if ((m->size + 1) * 10 >= m->cap * 7) {
    hmap_rehash(m, m->cap * 2);
  }

  size_t mask = m->cap - 1;
  size_t pos = (size_t)splitmix64(key) & mask;
  while (m->used[pos] && m->keys[pos] != key)
    pos = (pos + 1) & mask;

  if (!m->used[pos]) {
    int id = dsu_add(dsu);
    m->used[pos] = 1;
    m->keys[pos] = key;
    m->vals[pos] = id;
    m->size++;
    return id;
  }
  return m->vals[pos];
}

static uint64_t pack_key(const int *c, int n) {
  uint64_t key = 0;
  for (int i = 0; i < n; i++) {
    key |= ((uint64_t)(c[i] & 15)) << (4ULL * i);
  }

  return key;
}

int main(void) {
  int n;
  int mazeNo = 1;

  while (scanf("%d", &n) == 1) {
    if (n == 0)
      break;

    int s[10], t[10];
    for (int i = 0; i < n; i++)
      scanf("%d", &s[i]);
    for (int i = 0; i < n; i++)
      scanf("%d", &t[i]);

    DSU dsu;
    dsu_init(&dsu);

    HMap mp;
    hmap_init(&mp, 1024);

    int sid = hmap_get_or_add(&mp, &dsu, pack_key(s, n));
    int tid = hmap_get_or_add(&mp, &dsu, pack_key(t, n));

    while (1) {
      int first;
      scanf("%d", &first);
      if (first == -1)
        break;

      int a[10], b[10];
      a[0] = first;

      for (int i = 1; i < n; i++)
        scanf("%d", &a[i]);
      for (int i = 0; i < n; i++)
        scanf("%d", &b[i]);

      int uid = hmap_get_or_add(&mp, &dsu, pack_key(a, n));
      int vid = hmap_get_or_add(&mp, &dsu, pack_key(b, n));
      dsu_unite(&dsu, uid, vid);
    }

    int ok = (dsu_find(&dsu, sid) == dsu_find(&dsu, tid));
    printf("Maze #%d %s\n", mazeNo++,
           ok ? "can be travelled" : "cannot be travelled");
    hmap_free(&mp);
    dsu_free(&dsu);
  }

  return 0;
}