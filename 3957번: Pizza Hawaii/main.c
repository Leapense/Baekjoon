#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdbool.h>

#define MAXW 21
typedef struct { char w[MAXW]; uint64_t mask; } Item;
typedef struct { char a[MAXW], b[MAXW]; } Pair;

typedef struct {
	size_t cap;
	char (*key)[MAXW];
	uint64_t *mask;
	unsigned char *used;
} Table;

static uint64_t fnv1a64(const char *s) {
	uint64_t h = 1469598103934665603ULL;
	while (*s) { h ^= (unsigned char)(*s++); h *= 1099511628211ULL; }
	return h;
}

static void table_init(Table *t, size_t cap) {
	t->cap = cap;
	t->key = (char (*)[MAXW])calloc(cap, sizeof *t->key);
	t->mask = (uint64_t*)calloc(cap, sizeof *t->mask);
	t->used = (unsigned char*)calloc(cap, sizeof *t->used);
}

static void table_free(Table *t) {
	free(t->key); free(t->mask); free(t->used);
	t->key = NULL; t->mask = NULL; t->used = NULL; t->cap = 0;
}

static void table_put_or(Table *t, const char *k, uint64_t add) {
	size_t m = t->cap - 1;
	size_t i = (size_t)(fnv1a64(k) & m);
	while (t->used[i]) {
		if (strcmp(t->key[i], k) == 0) {
			t->mask[i] |= add;
			return;
		}
		i = (i + 1) & m;
	}
	t->used[i] = 1;
	strncpy(t->key[i], k, MAXW-1);
	t->key[i][MAXW-1] = '\0';
	t->mask[i] = add;
}

static void table_to_items(const Table *t, Item **out, size_t *outn) {
	size_t cnt = 0;
	for (size_t i = 0; i < t->cap; ++i) if (t->used[i]) ++cnt;
	Item *arr = (Item*)malloc(cnt * sizeof(Item));
	size_t p = 0;
	for (size_t i = 0; i < t->cap; ++i) if (t->used[i]) {
		strncpy(arr[p].w, t->key[i], MAXW);
		arr[p].w[MAXW - 1] = '\0';
		arr[p].mask = t->mask[i];
		++p;
	}
	*out = arr; *outn = cnt;
}

static int cmp_item(const void *a, const void *b) {
	const Item *x = (const Item*)a, *y = (const Item*)b;
	if (x->mask < y->mask) return -1;
	if (x->mask > y->mask) return 1;
	return strcmp(x->w, y->w);
}

static int cmp_pair(const void *a, const void *b) {
	const Pair *x = (const Pair*)a, *y = (const Pair*)b;
	int c = strcmp(x->a, y->a);
	if (c) return c;
	return strcmp(x->b, y->b);
}

int main(void) {
	int T;
	if (scanf("%d", &T) != 1) return 0;
	for (int tc = 0; tc < T; ++tc) {
		int n; scanf("%d", &n);

		Table FT, GT;
		table_init(&FT, 8192);
		table_init(&GT, 8192);

		for (int i = 0; i < n; ++i) {
			char name[32]; scanf("%31s", name);
			int m;

			scanf("%d", &m);
			for (int j = 0; j < m; ++j) {
				char w[MAXW]; scanf("%20s", w);
				table_put_or(&FT, w, (1ULL << i));
			}

			scanf("%d", &m);
			for (int j = 0; j < m; ++j) {
				char w[MAXW]; scanf("%20s", w);
				table_put_or(&GT, w, (1ULL << i));
			}
		}

		Item *F = NULL, *G = NULL;
		size_t Fn = 0, Gn = 0;
		table_to_items(&FT, &F, &Fn);
		table_to_items(&GT, &G, &Gn);
		table_free(&FT);
		table_free(&GT);

		qsort(F, Fn, sizeof(Item), cmp_item);
		qsort(G, Gn, sizeof(Item), cmp_item);
		size_t i = 0, j = 0;
		size_t cap = 1024, sz = 0;
		Pair *ans = (Pair*)malloc(cap * sizeof(Pair));

		while (i < Fn && j < Gn) {
			if (F[i].mask < G[j].mask) {
				uint64_t m = F[i].mask;
				while (i < Fn && F[i].mask == m) ++i;
			} else if (F[i].mask > G[j].mask) {
				uint64_t m = G[j].mask;
				while (j < Gn && G[j].mask == m) ++j;
			} else {
				uint64_t m = F[i].mask;
				size_t i2 = i, j2 = j;
				while (i2 < Fn && F[i2].mask == m) ++i2;
				while (j2 < Gn && G[j2].mask == m) ++j2;
				for (size_t a = i; a < i2; ++a)
					for (size_t b = j; b < j2; ++b) {
						if (sz == cap) {
							cap <<= 1;
							ans = (Pair*)realloc(ans, cap * sizeof(Pair));
						}
						strncpy(ans[sz].a, F[a].w, MAXW);
						ans[sz].a[MAXW-1] = '\0';
						strncpy(ans[sz].b, G[b].w, MAXW);
						ans[sz].b[MAXW-1] = '\0';
						++sz;
					}
				i = i2;
				j = j2;
			}
		}

		qsort(ans, sz, sizeof(Pair), cmp_pair);

		for (size_t k = 0; k < sz; ++k) {
			printf("(%s, %s)\n", ans[k].a, ans[k].b);
		}
		free(ans);
		free(F);
		free(G);

		if (tc + 1 < T) puts("");
	}
	return 0;
}
