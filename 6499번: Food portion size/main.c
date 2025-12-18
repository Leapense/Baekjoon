#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

typedef long long ll;

typedef struct {
  int p, q;
} Frac;

static int igcd(int a, int b) {
  while (b) {
    int t = a % b;
    a = b;
    b = t;
  }
  return a < 0 ? -a : a;
}

static ll llgcd(ll a, ll b) {
  if (a < 0)
    a = -a;
  if (b < 0)
    b = -b;
  while (b) {
    ll t = a % b;
    a = b;
    b = t;
  }
  return a;
}

static int frac_cmp_value(const void *A, const void *B) {
  const Frac *x = (const Frac *)A;
  const Frac *y = (const Frac *)B;

  int lhs = x->p * y->q;
  int rhs = y->p * x->q;
  if (lhs != rhs)
    return (lhs < rhs) ? -1 : 1;
  if (x->p != y->p)
    return (x->p < y->p) ? -1 : 1;
  if (x->q != y->q)
    return (x->q < y->q) ? -1 : 1;
  return 0;
}

int main(void) {
  int n;
  while (scanf("%d", &n) == 1) {
    if (n == 0)
      break;

    int a, b;
    scanf("%d %d", &a, &b);

    int *y = (int *)malloc(sizeof(int) * (size_t)n);
    ll Y = 0;
    for (int i = 0; i < n; i++) {
      scanf("%d", &y[i]);
      Y += y[i];
    }

    Frac *cand = (Frac *)malloc(sizeof(Frac) * (size_t)(3 * n));
    int m = 0;
    for (int i = 0; i < n; i++) {
      for (int t = 1; t <= 3; t++) {
        int p = y[i], q = t;
        int g = igcd(p, q);
        p /= g;
        q /= g;
        cand[m++] = (Frac){p, q};
      }
    }
    qsort(cand, (size_t)m, sizeof(Frac), frac_cmp_value);

    int u = 0;
    for (int i = 0; i < m; i++) {
      if (u == 0 || frac_cmp_value(&cand[i], &cand[u - 1]) != 0) {
        cand[u++] = cand[i];
      }
    }

    ll best_num = 0, best_den = 1;
    int has_best = 0;

    for (int idx = 0; idx < u; idx++) {
      int p = cand[idx].p;
      int q = cand[idx].q;

      ll K = 0;
      int ok = 1;
      for (int i = 0; i < n; i++) {
        int ki = (y[i] * q + p - 1) / p;
        if (ki > 3) {
          ok = 0;
          break;
        }
        K += ki;
      }

      if (!ok)
        continue;

      ll num = (ll)a * (ll)p * K + (ll)q * ((ll)b * K - (ll)a * Y);
      ll den = (ll)q;

      ll g = llgcd(num, den);
      num /= g;
      den /= g;

      if (!has_best) {
        best_num = num;
        best_den = den;
        has_best = 1;
      } else {
        __int128 left = (__int128)num * best_den;
        __int128 right = (__int128)best_num * den;
        if (left < right) {
          best_num = num;
          best_den = den;
        }
      }
    }

    if (best_den == 1) {
      printf("%lld\n", best_num);
    } else {
      printf("%lld / %lld\n", best_num, best_den);
    }

    free(y);
    free(cand);
  }

  return 0;
}